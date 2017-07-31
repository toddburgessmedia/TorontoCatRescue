package com.toddburgessmedia.torontocatrescue.presenter;

import android.os.Parcelable;

import com.toddburgessmedia.torontocatrescue.data.LimitedPetDetail;
import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;
import com.toddburgessmedia.torontocatrescue.view.PetDetailView;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 30/07/17.
 */

public interface PetDetailPresenter {

    void setPetDetailView(PetDetailView petDetailView);
    void setPetID(String petID);
    void getPetInformation();
    void updatePetInformation(PetDetailInfo petDetailInfo);
    void updateBondedPetInformation(LimitedPetDetail petDetailInfo);
    void onError();
    Parcelable saveInstancePet();
    Parcelable saveInstanceBondedFriend();
    void restoreInstancePet(Parcelable pet);
    void restoreBondedFriend(Parcelable friend);

}
