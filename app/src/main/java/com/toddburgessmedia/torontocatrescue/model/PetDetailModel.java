package com.toddburgessmedia.torontocatrescue.model;

import com.toddburgessmedia.torontocatrescue.data.LimitedPet;
import com.toddburgessmedia.torontocatrescue.data.PetDetail;
import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;
import com.toddburgessmedia.torontocatrescue.presenter.PetDetailPresenter;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 30/07/17.
 */

public interface PetDetailModel {

    void fetchPetDetail(String petID);
    PetDetail getPetDetail();
    void fetchLimitedPetDetail(String petID);
    LimitedPet getLimitedPet();
    void setPetDetailPresenter(PetDetailPresenter petDetailPresenter);
    boolean isBondedPair();
    void getBondedPet(PetDetailInfo info);
    String getCatName();
}
