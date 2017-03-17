package com.autohome.yxc.didi.di.components;

import android.app.Activity;


import com.autohome.yxc.didi.ui.activity.MainActivity;
import com.autohome.yxc.didi.di.modules.ActivityModule;
import com.autohome.yxc.didi.di.scops.PreActivity;

import dagger.Component;

/**
 * Description:
 * Creator: yxc
 * date: $date $time
 */
@PreActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);


}
