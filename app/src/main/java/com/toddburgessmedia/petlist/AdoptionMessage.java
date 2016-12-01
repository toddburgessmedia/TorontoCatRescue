package com.toddburgessmedia.petlist;

import com.toddburgessmedia.petlist.data.PetDetailInfo;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 29/11/16.
 */

public class AdoptionMessage {

    PetDetailInfo info;

    AdoptionMessage(PetDetailInfo info) {

        this.info = info;
    }

    public PetDetailInfo getInfo() {
        return info;
    }



}
