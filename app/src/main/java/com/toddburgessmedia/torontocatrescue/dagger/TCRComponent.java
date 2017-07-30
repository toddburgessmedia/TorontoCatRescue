package com.toddburgessmedia.torontocatrescue.dagger;

import com.toddburgessmedia.torontocatrescue.MainFragment;
import com.toddburgessmedia.torontocatrescue.PetDetailFragment;
import com.toddburgessmedia.torontocatrescue.TCRMain;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

@Singleton
@Component(modules =
            {AppModule.class,
            RetrofitModule.class,
            NavDrawerModule.class,
            PetListModule.class,
            PetDetailModule.class})

public interface TCRComponent {

    void inject(TCRMain tcrMain);

    void inject(MainFragment mainFragment);

    void inject(PetDetailFragment fragment);

}
