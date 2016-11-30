package com.toddburgessmedia.torontocatrescue;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.toddburgessmedia.torontocatrescue.dagger.AppModule;
import com.toddburgessmedia.torontocatrescue.dagger.DaggerTCRComponent;
import com.toddburgessmedia.torontocatrescue.dagger.NavDrawerModule;
import com.toddburgessmedia.torontocatrescue.dagger.NetModule;
import com.toddburgessmedia.torontocatrescue.dagger.TCRComponent;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

public class TorontoCatRescue extends Application {

    TCRComponent tcrComponent;
    Tracker tracker;

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

    public Tracker getTracker () {

        if (tracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            tracker = analytics.newTracker(R.xml.global_tracker);
        }

        return tracker;
    }
}
