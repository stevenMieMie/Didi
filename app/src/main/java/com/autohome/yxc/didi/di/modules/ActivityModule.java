package com.autohome.yxc.didi.di.modules;

import android.app.Activity;


import com.autohome.yxc.didi.di.scops.PreActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Creator: yxc
 * date: $date $time
 */

@Module
public class ActivityModule {
    Activity mActivity;
    public ActivityModule(Activity activity){this.mActivity = activity;}

    @Provides @PreActivity
    Activity provideActivity(){return mActivity;}
}
