package com.toddburgessmedia.torontocatrescue.view;

import com.toddburgessmedia.torontocatrescue.data.Pet;
import com.toddburgessmedia.torontocatrescue.data.PetList;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 24/07/17.
 */

public interface PetListView {

    void updatePetListView(PetList petList);
    void onError(Throwable t);
    void onClickPet(Pet pet);
}
