package com.toddburgessmedia.torontocatrescue;

import android.app.Application;

import com.toddburgessmedia.torontocatrescue.dagger.Injector;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

public class TorontoCatRescue extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Fabric.with(this, new Crashlytics());
        Injector.init(this);
    }
}
