package com.autohome.yxc.didi.di.components;

import android.content.Context;


import com.autohome.yxc.didi.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Description:
 * Creator: yxc
 * date: $date $time
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();
}
