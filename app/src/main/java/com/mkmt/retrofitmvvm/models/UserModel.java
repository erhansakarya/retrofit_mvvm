package com.mkmt.retrofitmvvm.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String hireable;
    private String bio;
    private String twitter_username;
    private Integer public_repos;
    private Integer public_gists;
    private Integer followers;
    private Integer following;

    public UserModel(String name, String company, String blog, String location, String email, String hireable, String bio, String twitter_username, Integer public_repos, Integer public_gists, Integer followers, Integer following) {
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.email = email;
        this.hireable = hireable;
        this.bio = bio;
        this.twitter_username = twitter_username;
        this.public_repos = public_repos;
        this.public_gists = public_gists;
        this.followers = followers;
        this.following = following;
    }

    protected UserModel(Parcel in) {
        name = in.readString();
        company = in.readString();
        blog = in.readString();
        location = in.readString();
        email = in.readString();
        hireable = in.readString();
        bio = in.readString();
        twitter_username = in.readString();
        if (in.readByte() == 0) {
            public_repos = null;
        } else {
            public_repos = in.readInt();
        }
        if (in.readByte() == 0) {
            public_gists = null;
        } else {
            public_gists = in.readInt();
        }
        if (in.readByte() == 0) {
            followers = null;
        } else {
            followers = in.readInt();
        }
        if (in.readByte() == 0) {
            following = null;
        } else {
            following = in.readInt();
        }
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    // getters
    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getHireable() {
        return hireable;
    }

    public String getBio() {
        return bio;
    }

    public String getTwitter_username() {
        return twitter_username;
    }

    public Integer getPublic_repos() {
        return public_repos;
    }

    public Integer getPublic_gists() {
        return public_gists;
    }

    public Integer getFollowers() {
        return followers;
    }

    public Integer getFollowing() {
        return following;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(company);
        dest.writeString(blog);
        dest.writeString(location);
        dest.writeString(email);
        dest.writeString(hireable);
        dest.writeString(bio);
        dest.writeString(twitter_username);
        if (public_repos == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(public_repos);
        }
        if (public_gists == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(public_gists);
        }
        if (followers == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(followers);
        }
        if (following == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(following);
        }
    }
}
