package com.xandone.dog.wcapp.di.component;

import com.xandone.dog.wcapp.api.http.RetrofitHelper;
import com.xandone.dog.wcapp.di.module.AppModule;
import com.xandone.dog.wcapp.di.module.HttpModule;
import com.xandone.dog.wcapp.model.DataManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author: xandone
 * created on: 2019/3/6 9:44
 */

@Singleton
@Component(modules =  {AppModule.class, HttpModule.class})
public interface AppComponent {
    DataManager getDataManager();

    RetrofitHelper retrofitHelper();
}
