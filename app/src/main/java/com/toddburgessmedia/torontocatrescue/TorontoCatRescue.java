package com.toddburgessmedia.torontocatrescue;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
import com.toddburgessmedia.torontocatrescue.dagger.Injector;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

public class TorontoCatRescue extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        LeakCanary.install(this);
        Injector.init(this);
    }
}
