package com.mobisys.android.upcoming_movies.assignment.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jain on 4/12/2016.
 */
public class Date implements Parcelable {
    private String maximum;
    private String minimum;

    public  Date(){}
    protected Date(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<Date> CREATOR = new Creator<Date>() {
        @Override
        public Date createFromParcel(Parcel in) {
            return new Date(in);
        }

        @Override
        public Date[] newArray(int size) {
            return new Date[size];
        }
    };

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        maximum = in.readString();
        minimum = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(maximum);
        dest.writeString(minimum);
    }
}
