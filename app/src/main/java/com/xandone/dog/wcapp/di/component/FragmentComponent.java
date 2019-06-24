package com.xandone.dog.wcapp.di.component;

import android.app.Activity;

import com.xandone.dog.wcapp.di.module.FragmentModule;
import com.xandone.dog.wcapp.di.scope.FragmentScope;
import com.xandone.dog.wcapp.ui.joke.JokeFragment;
import com.xandone.dog.wcapp.ui.personal.PersonalFragment;
import com.xandone.dog.wcapp.ui.personal.SelfJokeFragment;
import com.xandone.dog.wcapp.ui.video.VideoListFragment;

import dagger.Component;

/**
 * author: xandone
 * created on: 2019/3/6 9:42
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(JokeFragment jokeFragment);

    void inject(VideoListFragment videoListFragment);

    void inject(PersonalFragment personalFragment);

    void inject(SelfJokeFragment selfJokeFragment);
}
