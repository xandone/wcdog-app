package com.xandone.dog.wcapp.di.module;

import android.app.Activity;

import com.xandone.dog.wcapp.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * author: xandone
 * created on: 2019/3/6 8:42
 */

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return activity;
    }
}
