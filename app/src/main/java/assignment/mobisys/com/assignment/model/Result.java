package assignment.mobisys.com.assignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.codehaus.jackson.annotate.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import assignment.mobisys.com.assignment.Constants;

/**
 * Created by jain on 4/12/2016.
 */
public class Result  implements Parcelable{
    private String poster_path;
    private String adult;
    private String overview;
    private String release_date;
    private String id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private float popularity;
    private int vote_count;
    private float vote_average;

    public Result(){}

    protected Result(Parcel in) {
       readFromParcel(in);
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public String getPoster_path() {
        return Constants.IMAGE_BASE_URL+poster_path;
    }

    public String get_Backdrop_path() {
        return Constants.IMAGE_BASE_URL+backdrop_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String get_adult(){
        if(adult.equalsIgnoreCase("true")) {
            adult="(A)";
        } else {
            adult="(A/U)";
        }
        return  adult;

    }
    public String get_release_date(){
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 =new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date date=simpleDateFormat1.parse(release_date);
            release_date=simpleDateFormat2.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        poster_path = in.readString();
        adult = in.readString();
        overview = in.readString();
        release_date = in.readString();
        id = in.readString();
        original_title = in.readString();
        original_language = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
        popularity = in.readFloat();
        vote_count = in.readInt();
        vote_average = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster_path);
        dest.writeString(adult);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(id);
        dest.writeString(original_title);
        dest.writeString(original_language);
        dest.writeString(title);
        dest.writeString(backdrop_path);
        dest.writeFloat(popularity);
        dest.writeInt(vote_count);
        dest.writeFloat(vote_average);
    }
}
