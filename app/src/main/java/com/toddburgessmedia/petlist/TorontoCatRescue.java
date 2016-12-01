package com.toddburgessmedia.petlist;

import android.app.Application;

import com.toddburgessmedia.petlist.dagger.AppModule;
import com.toddburgessmedia.petlist.dagger.DaggerTCRComponent;
import com.toddburgessmedia.petlist.dagger.NavDrawerModule;
import com.toddburgessmedia.petlist.dagger.NetModule;
import com.toddburgessmedia.petlist.dagger.TCRComponent;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

public class TorontoCatRescue extends Application {

    TCRComponent tcrComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        tcrComponent = DaggerTCRComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .navDrawerModule(new NavDrawerModule())
                .build();
    }

    public TCRComponent getTcrComponent() {
        return tcrComponent;
    }
}
