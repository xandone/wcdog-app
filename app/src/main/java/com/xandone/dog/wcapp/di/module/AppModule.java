package com.xandone.dog.wcapp.di.module;

import com.xandone.dog.wcapp.api.http.HttpHelper;
import com.xandone.dog.wcapp.api.http.RetrofitHelper;
import com.xandone.dog.wcapp.model.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author: xandone
 * created on: 2019/3/6 8:46
 */

@Module
public class AppModule {
    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }
}
