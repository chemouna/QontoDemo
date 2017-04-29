package com.myapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Geo implements Parcelable {
    public static final Parcelable.Creator<Geo> CREATOR = new Parcelable.Creator<Geo>() {
        @Override
        public Geo createFromParcel(Parcel source) {
            return new Geo(source);
        }

        @Override
        public Geo[] newArray(int size) {
            return new Geo[size];
        }
    };
    private String lat;
    private String lng;

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    protected Geo(Parcel in) {
        this.lat = in.readString();
        this.lng = in.readString();
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lat);
        dest.writeString(this.lng);
    }
}
