package com.toddburgessmedia.torontocatrescue.dagger;

import com.toddburgessmedia.torontocatrescue.TorontoCatRescue;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 19/04/17.
 */

public final class Injector {

    private static Injector injector;

    private TCRComponent tcrComponent;

    private Injector (TorontoCatRescue torontoCatRescue) {
        tcrComponent = DaggerTCRComponent.builder()
                .appModule(new AppModule(torontoCatRescue))
                .netModule(new NetModule())
                .navDrawerModule(new NavDrawerModule())
                .build();
    }

    public static void init (TorontoCatRescue torontoCatRescue) {
        injector = new Injector(torontoCatRescue);
    }

    public static TCRComponent getAppComponent() {
        return injector.tcrComponent;
    }

}
