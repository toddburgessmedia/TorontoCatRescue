package com.toddburgessmedia.torontocatrescue.model;

import com.toddburgessmedia.torontocatrescue.data.PetList;
import com.toddburgessmedia.torontocatrescue.view.PetListView;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 24/07/17.
 */

public interface PetListDataModel {

    void fetchPetList();

    void setPetListView(PetListView petListView);

    PetList getPetList();
}
