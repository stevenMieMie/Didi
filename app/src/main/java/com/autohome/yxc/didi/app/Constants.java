package com.autohome.yxc.didi.app;

import java.io.File;

/**
 * Created by yuexingchuan on 17/3/15.
 */

public class Constants {
    //================= PATH ====================
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + File.separator + "NetCache";

    //PHONE_LOCATION
    public static final String PHONE_LOCATION = "PHONE_LOCATION";
}
