package com.toddburgessmedia.torontocatrescue.model;

import android.os.Parcelable;

import com.toddburgessmedia.torontocatrescue.data.LimitedPet;
import com.toddburgessmedia.torontocatrescue.data.PetDetail;
import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;
import com.toddburgessmedia.torontocatrescue.presenter.PetDetailPresenter;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 30/07/17.
 */

public interface PetDetailModel {

    void fetchPetDetail(String petID);
    void fetchPetDetailAll(String petID);
    PetDetail getPetDetail();
    void fetchBondedFriend(String petID);
    LimitedPet getBondedFriend();
    void setPetDetailPresenter(PetDetailPresenter petDetailPresenter);
    boolean isBondedPair();
    void getBondedPet(PetDetailInfo info);
    String getCatName();
    Parcelable savePetDetail();
    Parcelable saveBondedFriend();
    void restorePetDetail(Parcelable petDetail);
    void restoreBondedFriend(Parcelable bondedFriend);
}
