package com.autohome.yxc.didi.base;

/**
 * Description: resenter基类
 * Creator: yxc
 * date: 2017/03/16 9:14
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
