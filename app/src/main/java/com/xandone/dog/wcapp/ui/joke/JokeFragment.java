package com.xandone.dog.wcapp.ui.joke;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.flyco.tablayout.SlidingTabLayout;
import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseRxFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author: xandone
 * created on: 2019/3/6 13:34
 */

public class JokeFragment extends BaseRxFragment {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.joke_main_vp)
    ViewPager jokeMainVp;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;

    private ArrayList<Fragment> fragments;
    private static final String[] mTitles = {"全部", "经典", "荤段子", "精分", "脑残", "冷笑话"};

    @Override
    protected void initInject() {

    }

    @Override
    public int setLayout() {
        return R.layout.frag_joke_layout;
    }

    @Override
    public void initData() {
        super.initData();
        setToolBar(toolBar, "帖子");
        fragments = new ArrayList<>();
        fragments.add(JokeTagFragment.newInstance(JokeTagFragment.TYPE_JOKE_ALL));
        fragments.add(JokeTagFragment.newInstance(JokeTagFragment.TYPE_JOKE_CLASSIC));
        fragments.add(JokeTagFragment.newInstance(JokeTagFragment.TYPE_JOKE_YELLOW));
        fragments.add(JokeTagFragment.newInstance(JokeTagFragment.TYPE_JOKE_MIND));
        fragments.add(JokeTagFragment.newInstance(JokeTagFragment.TYPE_JOKE_SHITE));
        fragments.add(JokeTagFragment.newInstance(JokeTagFragment.TYPE_JOKE_COLD));
        MyPagerAdapter adapter = new MyPagerAdapter(getFragmentManager());
        jokeMainVp.setAdapter(adapter);
        tabLayout.setViewPager(jokeMainVp);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }
}
