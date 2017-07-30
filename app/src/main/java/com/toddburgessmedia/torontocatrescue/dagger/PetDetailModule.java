package com.toddburgessmedia.torontocatrescue.dagger;

import android.app.Application;

import com.toddburgessmedia.torontocatrescue.R;
import com.toddburgessmedia.torontocatrescue.model.PetDetailModel;
import com.toddburgessmedia.torontocatrescue.model.PetDetailModelImpl;
import com.toddburgessmedia.torontocatrescue.presenter.PetDetailPresenter;
import com.toddburgessmedia.torontocatrescue.presenter.PetDetailPresenterImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 30/07/17.
 */

@Module
public class PetDetailModule {

    PetDetailModule () {

    }

    @Provides
    PetDetailModel getPetDetailModel (Retrofit retrofit, Application application) {

        String apikey = application.getString(R.string.api_key);
        String shelterid = application.getString(R.string.shelter_id);

        return (PetDetailModel) new PetDetailModelImpl(retrofit, apikey, shelterid);

    }

    @Provides
    PetDetailPresenter getPetDetailPresenter (PetDetailModel petDetailModel) {

        return (PetDetailPresenter) new PetDetailPresenterImpl(petDetailModel);
    }
}
