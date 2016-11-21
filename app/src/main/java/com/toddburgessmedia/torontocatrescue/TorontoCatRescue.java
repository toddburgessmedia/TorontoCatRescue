package com.toddburgessmedia.torontocatrescue;

import android.app.Application;

import com.toddburgessmedia.torontocatrescue.dagger.AppModule;
import com.toddburgessmedia.torontocatrescue.dagger.DaggerTCRComponent;
import com.toddburgessmedia.torontocatrescue.dagger.NetModule;
import com.toddburgessmedia.torontocatrescue.dagger.TCRComponent;

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
                .build();
    }

    public TCRComponent getTcrComponent() {
        return tcrComponent;
    }
}
