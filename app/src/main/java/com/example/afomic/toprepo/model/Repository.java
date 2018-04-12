package com.example.afomic.toprepo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.afomic.toprepo.api.Item;

@Entity
public class Repository implements Parcelable {

    @PrimaryKey
    @NonNull
    private String name;
    private String ownerAvatarUrl;
    private String ownerName;
    private String createdAt;
    private String updatedAt;
    private int forkNumber;
    private int starNumber;
    private String description;

    public Repository(){

    }

    public Repository(Item repository){
        name=repository.getName();
        ownerName=repository.getOwner().getLogin();
        ownerAvatarUrl=repository.getOwner().getAvatarUrl();
        createdAt=repository.getCreatedAt().substring(0,10);
        updatedAt=repository.getPushedAt().substring(0,10);
        forkNumber=repository.getForks();
        starNumber=repository.getStargazersCount();
        description=repository.getDescription();
    }

    protected Repository(Parcel in) {
        name = in.readString();
        ownerAvatarUrl = in.readString();
        ownerName = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        forkNumber = in.readInt();
        starNumber = in.readInt();
        description= in.readString();
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerAvatarUrl() {
        return ownerAvatarUrl;
    }

    public void setOwnerAvatarUrl(String ownerAvatarUrl) {
        this.ownerAvatarUrl = ownerAvatarUrl;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getForkNumber() {
        return forkNumber;
    }

    public void setForkNumber(int forkNumber) {
        this.forkNumber = forkNumber;
    }

    public int getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(int starNumber) {
        this.starNumber = starNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(ownerAvatarUrl);
        dest.writeString(ownerName);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeInt(forkNumber);
        dest.writeInt(starNumber);
        dest.writeString(description);
    }
}
