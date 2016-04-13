package assignment.mobisys.com.assignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by jain on 4/12/2016.
 */
public class MovieResponse implements Parcelable {
    private int page;
    private ArrayList<Result> results;
    private Date dates;
    private int total_pages;
    private int total_results;

    public  MovieResponse(){}

    protected MovieResponse(Parcel in) {
        readFromParcel(in);
    }


    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        page = in.readInt();
        results = in.createTypedArrayList(Result.CREATOR);
        dates = in.readParcelable(Date.class.getClassLoader());
        total_pages = in.readInt();
        total_results = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeTypedList(results);
        dest.writeParcelable(dates, flags);
        dest.writeInt(total_pages);
        dest.writeInt(total_results);
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };
}
