package com.toddburgessmedia.torontocatrescue.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 25/11/16.
 */

public class LimitedPet implements Parcelable {

    @SerializedName("pet")
    LimitedPetDetail limitedPetDetail;

    @SerializedName("status")
    String status;

    public LimitedPetDetail getLimitedPetDetail() {
        return limitedPetDetail;
    }

    public void setLimitedPetDetail(LimitedPetDetail limitedPetDetail) {
        this.limitedPetDetail = limitedPetDetail;
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
        dest.writeParcelable(this.limitedPetDetail, flags);
        dest.writeString(this.status);
    }

    public LimitedPet() {
    }

    protected LimitedPet(Parcel in) {
        this.limitedPetDetail = in.readParcelable(LimitedPetDetail.class.getClassLoader());
        this.status = in.readString();
    }

    public static final Parcelable.Creator<LimitedPet> CREATOR = new Parcelable.Creator<LimitedPet>() {
        @Override
        public LimitedPet createFromParcel(Parcel source) {
            return new LimitedPet(source);
        }

        @Override
        public LimitedPet[] newArray(int size) {
            return new LimitedPet[size];
        }
    };
}
