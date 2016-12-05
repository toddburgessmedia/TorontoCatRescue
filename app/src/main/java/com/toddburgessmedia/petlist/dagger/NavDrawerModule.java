package com.toddburgessmedia.petlist.dagger;

import com.toddburgessmedia.petlist.R;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 28/11/16.
 */

@Module
public class NavDrawerModule {

    public NavDrawerModule() {

    }

    @Provides
    @Named("drawerIcons")
    public int[] getDrawerIcons () {

        int[] icons = { R.drawable.ic_bonded_pair,
                        R.drawable.ic_world,
                        R.drawable.ic_facebook,
                        R.drawable.ic_volunteer,
                        R.drawable.ic_donate};

        return icons;

    }

}
