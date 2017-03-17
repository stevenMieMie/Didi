package com.autohome.yxc.didi.presenter.contract;

import android.view.View;

import com.autohome.yxc.didi.base.BasePresenter;
import com.autohome.yxc.didi.base.BaseView;

/**
 * Description: 拆车坊_文章_Contract
 * Creator: yxc
 * date: 2016/11/23 13:30
 */

public interface ArticlesContract {
    interface View extends BaseView {
//        void showContent(List<InformationForCCF> list);
//
//        void showMoreContent(List<InformationForCCF> list);
    }

    interface Presenter extends BasePresenter<View> {
        void onRefresh();

        void loadMore();
    }
}
