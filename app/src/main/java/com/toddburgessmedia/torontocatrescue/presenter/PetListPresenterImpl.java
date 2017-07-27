package com.toddburgessmedia.torontocatrescue.presenter;

import com.toddburgessmedia.torontocatrescue.model.PetListDataModel;
import com.toddburgessmedia.torontocatrescue.view.PetListView;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 27/07/17.
 */

public class PetListPresenterImpl implements PetListPresenter {

        PetListDataModel petListModel;
        PetListView petListView;

        public PetListPresenterImpl (PetListDataModel petListModel) {

            this.petListModel = petListModel;
        }

        public void setPetListView (PetListView petListView) {

            this.petListView = petListView;
        }

        public void getPetList() {
            petListView.startProgressDialog();
            petListModel.fetchPetList();
        }

        public void updatePetList() {
            petListView.stopProgressDialog();
            petListView.updatePetList(petListModel.getPetList());

        }

}
