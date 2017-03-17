package com.autohome.yxc.didi.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autohome.yxc.didi.R;
import com.autohome.yxc.didi.app.App;
import com.autohome.yxc.didi.app.Constants;
import com.autohome.yxc.didi.base.SimpleActivity;
import com.autohome.yxc.didi.base.SimpleFragment;
import com.autohome.yxc.didi.ui.adapter.TitlePagerAdapter;
import com.autohome.yxc.didi.ui.fragment.BlankFragment;
import com.autohome.yxc.didi.ui.fragment.ExpressFragment;
import com.autohome.yxc.didi.util.PreUtils;
import com.autohome.yxc.didi.util.ToastUtil;
import com.autohome.yxc.didi.widget.UnScrollViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends SimpleActivity {

    @BindView(R.id.magic_indicator)
    MagicIndicator tab;
    @BindView(R.id.icon_category)
    ImageView iconCategory;
    @BindView(R.id.vp)
    UnScrollViewPager vp;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.rl_menu)
    RelativeLayout rlMenu;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout drawerLayout;
    TextView text;

    private Long firstTime = 0L;
    private String[] titles = new String[]{"快车", "出租车", "顺风车", "专车", "代驾", "试驾", "小巴", "公交", "自驾租车", "敬老出租"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        initPagers();
        setIndicator();
        initLeftLayout();
        tvLocation.setText(PreUtils.getString(mContext, Constants.PHONE_LOCATION,"未知"));
    }

    //加载片段列表
    private void initPagers() {
        List<SimpleFragment> fragments = new ArrayList<>();
        fragments.add(new ExpressFragment());
        for (int i = 0; i < titles.length-1; i++) {
            BlankFragment fragment = new BlankFragment();
            fragments.add(fragment);
        }
        vp.setAdapter(new TitlePagerAdapter(getSupportFragmentManager(), fragments, titles));
        vp.setOffscreenPageLimit(titles.length);
    }

    //设置指示器
    private void setIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titles == null ? 0 : titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.RED);
                colorTransitionPagerTitleView.setText(titles[index]);
                int padding = UIUtil.dip2px(context, 15.0D);
                colorTransitionPagerTitleView.setPadding(padding, 0, padding, 0);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vp.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        tab.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tab, vp);
    }

    public void initLeftLayout() {
        //设置透明，默认主要区域有因应覆盖
//        drawerLayout.setScrimColor(0x00000000);
        //左边菜单
        rlMenu = (RelativeLayout) findViewById(R.id.rl_menu);
        View view2 = getLayoutInflater().inflate(R.layout.menu_layout, null);
        rlMenu.addView(view2);
    }

    public void openLeftLayout() {
        if (drawerLayout.isDrawerOpen(rlMenu)) {
            drawerLayout.closeDrawer(rlMenu);
        } else {
            drawerLayout.openDrawer(rlMenu);

        }
    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 1500) {
            ToastUtil.shortShow("再按一次退出");
            firstTime = secondTime;
        } else {
            App.getInstance().exitApp();
        }
    }

    @OnClick({R.id.icon_category, R.id.ib_mine, R.id.ib_msg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_category:
                ToastUtil.shortShow("更多");
                break;
            case R.id.ib_mine:
                openLeftLayout();
                break;
            case R.id.ib_msg:
                ToastUtil.shortShow("消息");
                break;
        }
    }
}
