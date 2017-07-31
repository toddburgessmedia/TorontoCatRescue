package com.toddburgessmedia.torontocatrescue.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 22/11/16.
 */

public class PetDetail implements Parcelable {

    @SerializedName("pet")
    PetDetailInfo petDetailInfo;

    @SerializedName("status")
    String status;

    String petURL;

    public String getPetURL() {
        return petURL;
    }

    public void setPetURL(String petURL) {
        this.petURL = petURL;
    }



    public PetDetailInfo getPetDetailInfo() {

        if (petDetailInfo == null) {
            petDetailInfo = new PetDetailInfo();
        }

        return petDetailInfo;
    }

    public void setPetDetailInfo(PetDetailInfo petDetailInfo) {
        this.petDetailInfo = petDetailInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.petDetailInfo, flags);
        dest.writeString(this.status);
        dest.writeString(this.petURL);
    }

    public PetDetail() {
    }

    protected PetDetail(Parcel in) {
        this.petDetailInfo = in.readParcelable(PetDetailInfo.class.getClassLoader());
        this.status = in.readString();
        this.petURL = in.readString();
    }

    public static final Parcelable.Creator<PetDetail> CREATOR = new Parcelable.Creator<PetDetail>() {
        @Override
        public PetDetail createFromParcel(Parcel source) {
            return new PetDetail(source);
        }

        @Override
        public PetDetail[] newArray(int size) {
            return new PetDetail[size];
        }
    };
}
