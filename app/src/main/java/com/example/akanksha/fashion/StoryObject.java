package com.example.akanksha.fashion;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by akanksha on 02/04/16.
 */
public class StoryObject implements Parcelable {

        private String description = null;
        private String id = null;
        private String verb = null;
        private String db = null;
        private String url = null;
        private String si = null;  // ImageUrl
        private String type = null;
        private String title = null;
        private boolean like_flag;
        private int likes_count;
        private int comment_count;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVerb() {
            return verb;
        }

        public void setVerb(String verb) {
            this.verb = verb;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDb() {
            return db;
        }

        public void setDb(String db) {
            this.db = db;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSi() {
            return si;
        }

        public void setsi(String imageurl) {
            this.si = imageurl;
        }

        public boolean isLike_flag() {
            return like_flag;
        }

        public void setLike_flag(boolean like_flag) {
            this.like_flag = like_flag;
        }

        public int getLikes_count() {
            return likes_count;
        }

        public void setLikes_count(int likes_count) {
            this.likes_count = likes_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Parcelable.Creator<StoryObject> CREATOR = new Parcelable.Creator<StoryObject>() {
            public StoryObject createFromParcel(Parcel source) {
                StoryObject mStoryObject = new StoryObject();
                mStoryObject.description = source.readString();
                mStoryObject.id = source.readString();
                mStoryObject.db = source.readString();
                mStoryObject.url = source.readString();
                mStoryObject.verb = source.readString();
                mStoryObject.type = source.readString();
                mStoryObject.title = source.readString();
                mStoryObject.si = source.readString();
                mStoryObject.likes_count = source.readInt();
                mStoryObject.comment_count = source.readInt();
                return mStoryObject;
            }

            @Override
            public StoryObject[] newArray(int size) {
                return new StoryObject[size];
            }
        };

        public void writeToParcel(Parcel parcel, int flags) {

            parcel.writeString(description);
            parcel.writeString(id);
            parcel.writeString(db);
            parcel.writeString(url);
            parcel.writeString(verb);
            parcel.writeString(type);
            parcel.writeString(title);
            parcel.writeString(si);
            parcel.writeInt(likes_count);
            parcel.writeInt(comment_count);
        }


}
