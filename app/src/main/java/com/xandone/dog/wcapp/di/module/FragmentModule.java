package com.xandone.dog.wcapp.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.xandone.dog.wcapp.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * author: xandone
 * created on: 2019/3/6 9:42
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
