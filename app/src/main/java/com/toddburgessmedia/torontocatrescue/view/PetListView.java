package com.toddburgessmedia.torontocatrescue.view;

import com.toddburgessmedia.torontocatrescue.data.Pet;
import com.toddburgessmedia.torontocatrescue.data.PetList;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 24/07/17.
 */

public interface PetListView {

    void onError();
    void onClickPet(Pet pet);
    void startProgressDialog();
    void stopProgressDialog();
    void updatePetList(PetList petList);
}
