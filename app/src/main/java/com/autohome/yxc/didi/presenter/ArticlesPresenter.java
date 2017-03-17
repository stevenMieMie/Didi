package com.autohome.yxc.didi.presenter;

import com.autohome.yxc.didi.base.RxPresenter;
import com.autohome.yxc.didi.presenter.contract.ArticlesContract;

import javax.inject.Inject;

/**
 * Description: 拆车坊_文章_Presenter
 * Creator: yxc
 * date: 2016/11/24 14:47
 */

public class ArticlesPresenter extends RxPresenter<ArticlesContract.View> implements ArticlesContract.Presenter {

    public final int COUNT = 10;
    private int curPageIndex = 1;//默认第一页数据

    @Inject
    public ArticlesPresenter() {
    }

    @Override
    public void onRefresh() {
        curPageIndex = 1;
        getVideos();
    }

    @Override
    public void loadMore() {
        curPageIndex++;
        getVideos();
    }

    private void getVideos() {
//        Subscription rxSubscription = NewsApiImple.getArticles(COUNT, curPageIndex)
//                .subscribe(new ErSubscriber<List<InformationForCCF>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//                        if (curPageIndex > 1) {
//                            curPageIndex--;
//                        }
//                        mView.loadError(e.getMsg());
//                    }
//
//                    @Override
//                    public void onNext(List<InformationForCCF> informationForCCFs) {
//                        if (informationForCCFs != null) {
//                            if (curPageIndex == 1) {
//                                mView.showContent(informationForCCFs);
//                            } else {
//                                mView.showMoreContent(informationForCCFs);
//                            }
//                        }
//                    }
//                });
//        addSubscrebe(rxSubscription);
    }
}
