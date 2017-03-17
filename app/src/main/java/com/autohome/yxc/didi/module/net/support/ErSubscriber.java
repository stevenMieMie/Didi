package com.autohome.yxc.didi.module.net.support;


import com.autohome.yxc.didi.module.net.exception.ApiException;

import rx.Subscriber;

/**
 * Description: 用于把默认异常转换为自定义的异常显示内容
 * Creator: yxc
 * date: 2017/03/16 9:14
 */
public abstract class ErSubscriber<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
//        e.printStackTrace();
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e, 123));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}
