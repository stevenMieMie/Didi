package com.autohome.yxc.didi.util;

import android.content.Context;
import android.content.Intent;

import com.autohome.yxc.didi.ui.activity.MainActivity;
import com.autohome.yxc.didi.ui.activity.WelcomeActivity;

/**
 * Created by yuexingchuan on 17/3/16.
 */

public class JumpUtil {
    public static void go2MainActivity(Context context) {
        jump(context, MainActivity.class);
        ((WelcomeActivity) context).finish();
    }

    private static void jump(Context a, Class<?> clazz) {
        Intent intent = new Intent(a, clazz);
        a.startActivity(intent);
    }
}
