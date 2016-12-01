package com.toddburgessmedia.petlist.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 22/11/16.
 */

public class PetDetail {

    @SerializedName("pet")
    PetDetailInfo petDetailInfo;

    @SerializedName("status")
    String status;

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
