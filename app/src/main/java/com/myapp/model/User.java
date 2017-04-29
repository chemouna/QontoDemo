package com.myapp.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.myapp.data.OkCursor;
import com.squareup.sqlbrite.BriteDatabase;
import javax.annotation.concurrent.Immutable;
import rx.functions.Func1;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;

@Immutable public final class User implements Parcelable {

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

    public static final String TABLE = "user";

    public static final String ID = "user_id";
    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String WEBSITE = "website";

    public static final String CREATE_USER = ""
        + "CREATE TABLE "
        + TABLE
        + "("
        + ID
        + " INTEGER NOT NULL PRIMARY KEY,"
        + NAME
        + " TEXT NOT NULL, "
        + USERNAME
        + " TEXT, "
        + EMAIL
        + " TEXT, "
        + PHONE
        + " TEXT, "
        + WEBSITE
        + " TEXT "
        + " )";

    private final int id;
    private final String name;
    private final String username;
    private final String email;
    @Nullable private final Address address;
    private final String phone;
    private final String website;
    @Nullable private final Company company;

    public User(int id, String name, String username, String email, @Nullable Address address,
        String phone, String website, @Nullable Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    public static final String GET_USERS = "SELECT * FROM " + User.TABLE;

    public static Func1<Cursor, User> MAPPER = cursor -> create(new OkCursor(cursor));

    public static User create(OkCursor okCursor) {
        int id = okCursor.getInt(User.ID);
        String name = okCursor.getString(User.NAME);
        String username = okCursor.getString(User.USERNAME);
        String email = okCursor.getString(User.EMAIL);
        String phone = okCursor.getString(User.PHONE);
        String website = okCursor.getString(User.WEBSITE);
        return new User(id, name, username, email, null, phone, website, null);
    }

    public long insertInto(BriteDatabase db) {
        return db.insert(User.TABLE, toContentValues(), CONFLICT_REPLACE);
    }

    private ContentValues toContentValues() {
        Builder builder = new Builder();
        if (id > 0) {
            builder = builder.id(id);
        }
        return builder.name(name)
            .username(username)
            .email(email)
            .phone(phone)
            .website(website)
            .build();
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

    @Nullable
    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    @Nullable
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

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder id(long id) {
            values.put(ID, id);
            return this;
        }

        public Builder name(String name) {
            values.put(NAME, name);
            return this;
        }

        public Builder username(String username) {
            values.put(USERNAME, username);
            return this;
        }

        public Builder email(@Nullable String email) {
            values.put(EMAIL, email);
            return this;
        }

        public Builder phone(@Nullable String phone) {
            values.put(PHONE, phone);
            return this;
        }

        public Builder website(@Nullable String website) {
            values.put(WEBSITE, website);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}

