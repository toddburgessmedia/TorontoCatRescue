package com.toddburgessmedia.torontocatrescue.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application getApplication() {
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences getSharedPreferences() {
        return application.getSharedPreferences("tcr", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public WindowManager getWindowManager (Application application) {

        return (WindowManager) application.getSystemService(Context.WINDOW_SERVICE);
    }

}
