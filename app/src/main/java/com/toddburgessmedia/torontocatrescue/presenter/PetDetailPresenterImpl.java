package com.toddburgessmedia.torontocatrescue.presenter;

import com.toddburgessmedia.torontocatrescue.data.LimitedPetDetail;
import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;
import com.toddburgessmedia.torontocatrescue.model.PetDetailModel;
import com.toddburgessmedia.torontocatrescue.view.PetDetailView;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 30/07/17.
 */

public class PetDetailPresenterImpl implements PetDetailPresenter {

    PetDetailModel model;
    PetDetailView view;

    String petID;

    public PetDetailPresenterImpl (PetDetailModel petDetailModel) {

        this.model = petDetailModel;
        this.model.setPetDetailPresenter(this);
    }

    @Override
    public void setPetDetailView(PetDetailView petDetailView) {
        this.view = petDetailView;
    }

    @Override
    public void setPetID(String petID) {

        this.petID = petID;
    }

    @Override
    public void getPetInformation() {

        view.startProgressDialog();
        model.fetchPetDetailAll(petID);
    }

    @Override
    public void updatePetInformation(PetDetailInfo petDetailInfo) {

        view.stopProgressDialog();
        view.updateView(petDetailInfo);

        if (model.isBondedPair()) {
            model.getBondedPet(petDetailInfo);
        }
    }

    @Override
    public void updateBondedPetInformation(LimitedPetDetail petDetailInfo) {

        view.addBondedCardView(petDetailInfo,model.getCatName());
    }

    @Override
    public void onError() {
        view.createErrorToast();
    }
}
