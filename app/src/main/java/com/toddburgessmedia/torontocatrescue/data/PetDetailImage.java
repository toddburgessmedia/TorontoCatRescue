package com.toddburgessmedia.torontocatrescue.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 22/11/16.
 */

public class PetDetailImage {

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
}
