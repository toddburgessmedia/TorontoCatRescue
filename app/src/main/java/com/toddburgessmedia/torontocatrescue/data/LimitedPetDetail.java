package com.toddburgessmedia.torontocatrescue.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 25/11/16.
 */

public class LimitedPetDetail implements Parcelable {

    @SerializedName("pet_id")
    String petID;

    @SerializedName("pet_name")
    String petName;

    @SerializedName("images")
    ArrayList<PetDetailImage> images;

    @SerializedName("pet_details_url")
    String petDetailsUrl;

    public ArrayList<PetDetailImage> getImages() {
        return images;
    }

    public String getPetDetailsUrl() {
        return petDetailsUrl;
    }

    public void setPetDetailsUrl(String petDetailsUrl) {
        this.petDetailsUrl = petDetailsUrl;
    }

    public void setImages(ArrayList<PetDetailImage> images) {
        this.images = images;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.petID);
        dest.writeString(this.petName);
        dest.writeTypedList(this.images);
        dest.writeString(this.petDetailsUrl);
    }

    public LimitedPetDetail() {
    }

    protected LimitedPetDetail(Parcel in) {
        this.petID = in.readString();
        this.petName = in.readString();
        this.images = in.createTypedArrayList(PetDetailImage.CREATOR);
        this.petDetailsUrl = in.readString();
    }

    public static final Parcelable.Creator<LimitedPetDetail> CREATOR = new Parcelable.Creator<LimitedPetDetail>() {
        @Override
        public LimitedPetDetail createFromParcel(Parcel source) {
            return new LimitedPetDetail(source);
        }

        @Override
        public LimitedPetDetail[] newArray(int size) {
            return new LimitedPetDetail[size];
        }
    };
}
