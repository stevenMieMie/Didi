package com.autohome.yxc.didi.module.net;

import com.autohome.yxc.didi.module.net.api.NewsApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description: retrofit工具类
 * Creator: yxc
 * date: 2017/03/16 9:14
 */
public class RetrofitHelper {

    private static OkHttpClient okHttpClient = null;
    private static NewsApi newsApi;

    public static NewsApi getNewsApi() {
        initOkHttp();
        if (newsApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(NewsApi.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            newsApi = retrofit.create(NewsApi.class);
        }
        return newsApi;
    }

    static void initOkHttp() {
        okHttpClient = OkhttpHelper.initOkHttp(okHttpClient);
    }
}
