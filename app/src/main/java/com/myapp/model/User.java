package com.myapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class User implements Parcelable {

    private final int id;
    private final String name;
    private final String username;
    private final String email;
    private final Address address;
    private final String phone;
    private final String website;
    private final Company company;

    public User(int id, String name, String username, String email, Address address, String phone,
        String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.username);
        dest.writeString(this.email);
        dest.writeParcelable(this.address, flags);
        dest.writeString(this.phone);
        dest.writeString(this.website);
        dest.writeParcelable(this.company, flags);
    }

    protected User(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.username = in.readString();
        this.email = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.phone = in.readString();
        this.website = in.readString();
        this.company = in.readParcelable(Company.class.getClassLoader());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

