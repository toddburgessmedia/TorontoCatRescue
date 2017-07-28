package com.toddburgessmedia.torontocatrescue.presenter;

import android.os.Parcelable;

import com.toddburgessmedia.torontocatrescue.model.PetListModel;
import com.toddburgessmedia.torontocatrescue.view.PetListView;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 27/07/17.
 */

public class PetListPresenterImpl implements PetListPresenter {

    PetListModel petListModel;
    PetListView petListView;

    public PetListPresenterImpl (PetListModel petListModel) {

        this.petListModel = petListModel;
        this.petListModel.setPresenter(this);
    }

    @Override
    public void setPetListView (PetListView petListView) {

        this.petListView = petListView;
    }

    @Override
    public void getPetList() {
        petListView.startProgressDialog();
        petListModel.fetchPetList();
    }

    @Override
    public void updatePetList() {
        petListView.stopProgressDialog();
        petListView.updatePetList(petListModel.getPetList());

    }

    @Override
    public void onError() {
        petListView.stopProgressDialog();
        petListView.onError();
    }

    @Override
    public Parcelable saveInstanceState() {

        return petListModel.getPetListParcelable();
    }

    @Override
    public void restoreInstanceState(Parcelable parcelable) {
        petListModel.setPetListParcelable(parcelable);
        petListView.updatePetList(petListModel.getPetList());
    }

    @Override
    public void getPetsbySexAge(String sex, String age) {

        if (petListModel.isPetListEmpty()) {
            return;
        }

        petListView.updatePetList(petListModel.getPetsbySexAge(sex,age));

    }
}
