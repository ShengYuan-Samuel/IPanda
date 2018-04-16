package com.jiyun.ipanda.view.pandaliveview.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class VpAdapter extends FragmentPagerAdapter{
        private List<String> mTitle;
            private List<Fragment> mList;

            public VpAdapter(FragmentManager fm, List<String> mTitle, List<Fragment> mList) {
                super(fm);
                this.mTitle = mTitle;
                this.mList = mList;
            }

            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.isEmpty() ? 0 : mList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
}
