package com.toddburgessmedia.petlist.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 25/11/16.
 */

public class LimitedPet {

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
}
