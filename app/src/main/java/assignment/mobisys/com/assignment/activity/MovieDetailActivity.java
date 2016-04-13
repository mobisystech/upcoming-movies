package assignment.mobisys.com.assignment.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import assignment.mobisys.com.assignment.Constants;
import assignment.mobisys.com.assignment.R;
import assignment.mobisys.com.assignment.model.Image;
import assignment.mobisys.com.assignment.model.ImageResponse;
import assignment.mobisys.com.assignment.model.Result;
import assignment.mobisys.com.assignment.rest.RestCallback;
import assignment.mobisys.com.assignment.rest.RestClient;
import assignment.mobisys.com.assignment.utils.DialogUtil;
import assignment.mobisys.com.assignment.utils.MImageLoader;
import assignment.mobisys.com.assignment.widget.ProgressDialog;
import retrofit.client.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private Dialog mProgressDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        getSupportActionBar().hide();
        getMovieDetails();
    }

    private void getMovieDetails() {
        Result result=getIntent().getExtras().getParcelable(Constants.MOVIE);
        mProgressDailog = ProgressDialog.show(MovieDetailActivity.this, getResources().getString(R.string.please_wait));
        RestClient.AssignmentApi(MovieDetailActivity.this).movieDetail(Constants.API_KEY, result.getId(), new RestCallback<Result>() {
            @Override
            public void failure(String restErrors, boolean networkError) {
                if (mProgressDailog != null && mProgressDailog.isShowing()) mProgressDailog.dismiss();
                DialogUtil.showErrorDialog(MovieDetailActivity.this,Constants.ERROR,restErrors);
            }

            @Override
            public void success(Result result, Response response) {
                if (mProgressDailog != null && mProgressDailog.isShowing()) mProgressDailog.dismiss();
                initScreen(result);
            }
        });
    }

    private void initScreen(Result result) {
        findViewById(R.id.mainlayout).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.txt_center)).setText(result.getTitle());
        ((TextView)findViewById(R.id.txtTitle)).setText(result.getTitle());
        ((TextView)findViewById(R.id.txtOverview)).setText(result.getOverview());
        ((RatingBar)findViewById(R.id.ratingBar)).setRating(result.getPopularity());

        RestClient.AssignmentApi(MovieDetailActivity.this).getImages(Constants.API_KEY, result.getId(), new RestCallback<ImageResponse>() {
            @Override
            public void failure(String restErrors, boolean networkError) {
                if (mProgressDailog != null && mProgressDailog.isShowing()) mProgressDailog.dismiss();
                DialogUtil.showErrorDialog(MovieDetailActivity.this,Constants.ERROR,restErrors);
            }

            @Override
            public void success(ImageResponse result, Response response) {
                if (mProgressDailog != null && mProgressDailog.isShowing()) mProgressDailog.dismiss();
                ArrayList<Image> imageArrayList=new ArrayList<Image>();
                imageArrayList.addAll(result.getPosters());
                imageArrayList.addAll(result.getBackdrops());
                ViewPagerAdapter adp=new ViewPagerAdapter(MovieDetailActivity.this,imageArrayList);
                ViewPager pager=(ViewPager)findViewById(R.id.pager);
                pager.setOffscreenPageLimit(5);
                pager.setAdapter(adp);
                ((CirclePageIndicator)findViewById(R.id.indicator)).setViewPager(pager);

            }
        });
    }

    public class ViewPagerAdapter extends PagerAdapter {

        private Context mContext;
        private ArrayList<Image> mImageArrayList;

        public ViewPagerAdapter(Context mContext,ArrayList<Image> imageArrayList) {
            this.mContext = mContext;
            this.mImageArrayList = imageArrayList;
        }

        @Override
        public int getCount() {
             if(mImageArrayList.size()>=5){
                 return 5;
             }else{
                 return  mImageArrayList.size();
             }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);
            final ImageView imageView = (ImageView) itemView.findViewById(R.id.img_movie_poster);
            MImageLoader.displayImage(MovieDetailActivity.this, mImageArrayList.get(position).getFile_path(), imageView, 0, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    imageView.setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.progress_bar).setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });
            container.addView(itemView);
            return itemView;
        }
    }
}
