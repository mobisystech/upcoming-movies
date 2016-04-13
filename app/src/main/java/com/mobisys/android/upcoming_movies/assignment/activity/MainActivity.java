package com.mobisys.android.upcoming_movies.assignment.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import com.mobisys.android.upcoming_movies.assignment.Constants;
import com.mobisys.android.upcoming_movies.assignment.R;
import com.mobisys.android.upcoming_movies.assignment.model.MovieResponse;
import com.mobisys.android.upcoming_movies.assignment.model.Movie;
import com.mobisys.android.upcoming_movies.assignment.rest.RestCallback;
import com.mobisys.android.upcoming_movies.assignment.rest.RestClient;
import com.mobisys.android.upcoming_movies.assignment.utils.DialogUtil;
import com.mobisys.android.upcoming_movies.assignment.utils.MImageLoader;
import com.mobisys.android.upcoming_movies.assignment.widget.ProgressDialog;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private Dialog mProgressDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initScreen();

        findViewById(R.id.btnRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,InfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initScreen() {
        getSupportActionBar().hide();
        mProgressDailog = ProgressDialog.show(MainActivity.this, getResources().getString(R.string.please_wait));
        RestClient.getMovieApi(MainActivity.this).upcomingMovies(Constants.API_KEY, new RestCallback<MovieResponse>() {
            @Override
            public void failure(String restErrors, boolean networkError) {
                if (mProgressDailog != null && mProgressDailog.isShowing()) mProgressDailog.dismiss();
                DialogUtil.showErrorDialog(MainActivity.this,Constants.ERROR,restErrors);
            }

            @Override
            public void success(MovieResponse movieResponse, Response response) {
                if (mProgressDailog != null && mProgressDailog.isShowing()) mProgressDailog.dismiss();
                MovieAdpater movieAdpater=new MovieAdpater(MainActivity.this,0,movieResponse.getResults());
                ((ListView)findViewById(R.id.list_movies)).setAdapter(movieAdpater);
            }
        });
    }

    private class MovieAdpater extends ArrayAdapter<Movie> {
        private LayoutInflater mInflater;

        public MovieAdpater(Context context, int resource, List<Movie> objects) {
            super(context, resource, objects);
            mInflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        }

        private class ViewHolder {
            public ImageView imgMovie;
            public TextView txtMovieTitle, txtReleaseDate,txtAdult;

            public ViewHolder(View row) {
                imgMovie = (ImageView) row.findViewById(R.id.img_movie);
                txtMovieTitle = (TextView) row.findViewById(R.id.txt_movie_title);
                txtReleaseDate = (TextView) row.findViewById(R.id.txt_realese_date);
                txtAdult = (TextView) row.findViewById(R.id.txt_adult);
            }
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            if (row == null) {
                row = mInflater.inflate(R.layout.row_movie, null);
                holder = new ViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }
            final Movie rowItem = getItem(position);
            holder.txtMovieTitle.setText(rowItem.getTitle());
            holder.txtAdult.setText(rowItem.get_adult());
            holder.txtReleaseDate.setText(rowItem.get_release_date());
            MImageLoader.displayImage(MainActivity.this, rowItem.getPoster_path(), holder.imgMovie, 0);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,MovieDetailActivity.class);
                    intent.putExtra(Movie.MOVIE,rowItem);
                    startActivity(intent);
                }
            });
            return row;
        }
    }
}
