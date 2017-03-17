package com.autohome.yxc.didi.di.components;

import android.app.Activity;


import com.autohome.yxc.didi.di.modules.FragmentModule;
import com.autohome.yxc.didi.di.scops.PreFragment;

import dagger.Component;

/**
 * Description:
 * Creator: yxc
 * date: $date $time
 */
@PreFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

//    void inject(InfoFragment informationFragment);


}
