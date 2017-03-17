package com.autohome.yxc.didi.di.modules;

import android.app.Activity;
import android.support.v4.app.Fragment;


import com.autohome.yxc.didi.di.scops.PreFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Creator: yxc
 * date: $date $time
 */

@Module
public class FragmentModule {
    Fragment mFragment;
    public FragmentModule(Fragment fragment){mFragment = fragment;}

    @PreFragment
    @Provides
    Activity provideActivity(){return mFragment.getActivity();}
}
