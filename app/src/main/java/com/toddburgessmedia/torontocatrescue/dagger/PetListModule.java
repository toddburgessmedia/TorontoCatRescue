package com.toddburgessmedia.torontocatrescue.dagger;

import android.app.Application;

import com.toddburgessmedia.torontocatrescue.R;
import com.toddburgessmedia.torontocatrescue.model.PetListModel;
import com.toddburgessmedia.torontocatrescue.model.PetListModelImpl;
import com.toddburgessmedia.torontocatrescue.presenter.PetListPresenter;
import com.toddburgessmedia.torontocatrescue.presenter.PetListPresenterImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 30/07/17.
 */

@Module
public class PetListModule {

    @Provides
    public PetListModelImpl getPetListModel (Retrofit retrofit, Application application) {

        String apikey = application.getString(R.string.api_key);
        String shelterid = application.getString(R.string.shelter_id);

        return new PetListModelImpl(retrofit, apikey, shelterid);
    }

    @Provides
    public PetListModel getPetListDataModel (Retrofit retrofit, Application application) {

        String apikey = application.getString(R.string.api_key);
        String shelterid = application.getString(R.string.shelter_id);

        return (PetListModel) new PetListModelImpl(retrofit, apikey, shelterid);
    }

    @Provides
    public PetListPresenter getPetListPresenter (PetListModel petListModel) {

        PetListPresenter presenter = (PetListPresenter) new PetListPresenterImpl(petListModel);

        return presenter;
    }


}
