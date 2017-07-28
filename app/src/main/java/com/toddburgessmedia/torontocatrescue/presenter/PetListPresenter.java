package com.toddburgessmedia.torontocatrescue.presenter;

import com.toddburgessmedia.torontocatrescue.view.PetListView;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 27/07/17.
 */

public interface PetListPresenter {

    void setPetListView(PetListView petListView);
    void getPetList();
    void updatePetList();
    void onError();

}
