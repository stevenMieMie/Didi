package com.autohome.yxc.didi.ui.activity;

import android.os.Bundle;

import com.autohome.yxc.didi.R;
import com.autohome.yxc.didi.app.Constants;
import com.autohome.yxc.didi.base.SimpleActivity;
import com.autohome.yxc.didi.util.JumpUtil;
import com.autohome.yxc.didi.util.map.LocationUtil;
import com.autohome.yxc.didi.util.PreUtils;
import com.autohome.yxc.didi.util.RxUtil;
import com.jrummyapps.android.widget.AnimatedSvgView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class WelcomeActivity extends SimpleActivity {
    Subscription rxSubscription;

    @BindView(R.id.animated_svg_view)
    AnimatedSvgView svgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventAndData() {

        LocationUtil.getInstance().initLocation(new LocationUtil.ILocation() {
            @Override
            public void callback(String locResult) {
                PreUtils.putString(mContext, Constants.PHONE_LOCATION, locResult);
            }
        });

        //500毫秒后触发动画
        rxSubscription = Observable.timer(500, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        svgView.start();
                    }
                });
        //监听动画结束进入首页
        svgView.setOnStateChangeListener(new AnimatedSvgView.OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if (state == AnimatedSvgView.STATE_FINISHED)
                    JumpUtil.go2MainActivity(mContext);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (rxSubscription != null)
            rxSubscription.unsubscribe();
    }
}