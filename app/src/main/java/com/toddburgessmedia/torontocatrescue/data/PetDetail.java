package com.toddburgessmedia.torontocatrescue.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 22/11/16.
 */

public class PetDetail {

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
}
