package com.autohome.yxc.didi.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.autohome.yxc.didi.base.BaseFragment;
import com.autohome.yxc.didi.base.SimpleFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 */
public class TitlePagerAdapter extends FragmentPagerAdapter {

    private List<SimpleFragment> fragments;
    private String[] titles;

    public TitlePagerAdapter(FragmentManager fm, List<SimpleFragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public SimpleFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles == null ? "" : titles[position];
    }

}
