package com.toddburgessmedia.torontocatrescue.model;

import android.os.Parcelable;

import com.toddburgessmedia.torontocatrescue.data.PetList;
import com.toddburgessmedia.torontocatrescue.presenter.PetListPresenter;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 24/07/17.
 */

public interface PetListModel {

    void setPresenter(PetListPresenter presenter);

    void fetchPetList();

    PetList getPetList();

    Parcelable getPetListParcelable();

    void setPetListParcelable(Parcelable petListParcelable);
}
