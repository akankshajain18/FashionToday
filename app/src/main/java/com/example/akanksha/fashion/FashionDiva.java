package com.example.akanksha.fashion;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Created by akanksha on 29/03/16.
 *
 */

public class FashionDiva implements Parcelable {

    private String about = null;
    private String id = null;
    private String username = null;
    private int followers;
    private int following;
    private String image = null;
    private String url = null;
    private String handle = null;
    private boolean is_following;
    private long createdOn;

    public FashionDiva() {
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public boolean is_following() {
        return is_following;
    }

    public void setIs_following(boolean is_following) {
        this.is_following = is_following;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<FashionDiva> CREATOR = new Creator<FashionDiva>() {
        public FashionDiva createFromParcel(Parcel source) {
            FashionDiva mFashionDiva = new FashionDiva();
            mFashionDiva.about = source.readString();
            mFashionDiva.id = source.readString();
            mFashionDiva.username = source.readString();
            mFashionDiva.followers = source.readInt();
            mFashionDiva.following = source.readInt();
            mFashionDiva.image = source.readString();
            mFashionDiva.url = source.readString();
            mFashionDiva.handle = source.readString();
            mFashionDiva.createdOn = source.readLong();

            return mFashionDiva;
        }

        @Override
        public FashionDiva[] newArray(int size) {
            return new FashionDiva[size];
        }
    };

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(about);
        parcel.writeString(id);
        parcel.writeString(username);
        parcel.writeInt(followers);
        parcel.writeInt(following);
        parcel.writeString(image);
        parcel.writeString(url);
        parcel.writeString(handle);
        parcel.writeLong(createdOn);
    }
}
