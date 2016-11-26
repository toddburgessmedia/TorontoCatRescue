package com.toddburgessmedia.torontocatrescue.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 22/11/16.
 */

public class PetDetailImage implements Parcelable {

    @SerializedName("original_width")
    int originalWidth;

    @SerializedName("thumbnail_width")
    int thumbnailWidth;

    @SerializedName("thumbnail_url")
    String thumbnailUrl;

    @SerializedName("thumbnail_height")
    int thumbnailHeight;

    @SerializedName("original_url")
    String originalUrl;

    @SerializedName("original_height")
    int getOriginalHeight;

    public int getGetOriginalHeight() {
        return getOriginalHeight;
    }

    public void setGetOriginalHeight(int getOriginalHeight) {
        this.getOriginalHeight = getOriginalHeight;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public int getOriginalWidth() {
        return originalWidth;
    }

    public void setOriginalWidth(int originalWidth) {
        this.originalWidth = originalWidth;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(int thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(int thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.originalWidth);
        dest.writeInt(this.thumbnailWidth);
        dest.writeString(this.thumbnailUrl);
        dest.writeInt(this.thumbnailHeight);
        dest.writeString(this.originalUrl);
        dest.writeInt(this.getOriginalHeight);
    }

    public PetDetailImage() {
    }

    protected PetDetailImage(Parcel in) {
        this.originalWidth = in.readInt();
        this.thumbnailWidth = in.readInt();
        this.thumbnailUrl = in.readString();
        this.thumbnailHeight = in.readInt();
        this.originalUrl = in.readString();
        this.getOriginalHeight = in.readInt();
    }

    public static final Parcelable.Creator<PetDetailImage> CREATOR = new Parcelable.Creator<PetDetailImage>() {
        @Override
        public PetDetailImage createFromParcel(Parcel source) {
            return new PetDetailImage(source);
        }

        @Override
        public PetDetailImage[] newArray(int size) {
            return new PetDetailImage[size];
        }
    };
}
