package com.toddburgessmedia.petlist.dagger;

import com.toddburgessmedia.petlist.MainFragment;
import com.toddburgessmedia.petlist.PetDetailFragment;
import com.toddburgessmedia.petlist.PetListMain;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

@Singleton
@Component(modules = {AppModule.class,NetModule.class,NavDrawerModule.class})

public interface TCRComponent {

    void inject(PetListMain tcrMain);

    void inject(MainFragment mainFragment);

    void inject(PetDetailFragment fragment);

}
