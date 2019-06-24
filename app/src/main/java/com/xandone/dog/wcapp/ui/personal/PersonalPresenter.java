package com.xandone.dog.wcapp.ui.personal;


import com.xandone.dog.wcapp.base.RxPresenter;
import com.xandone.dog.wcapp.model.DataManager;

import java.util.Map;

import javax.inject.Inject;

import okhttp3.MultipartBody;

/**
 * author: xandone
 * Created on: 2018/5/9 17:12
 */

public class PersonalPresenter extends RxPresenter<PersonalContact.MyView> implements PersonalContact.Presenter {
    private DataManager dataManager;

    @Inject
    public PersonalPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void changeUserIcon(Map<String, String> maps, MultipartBody.Part part) {

    }
}
