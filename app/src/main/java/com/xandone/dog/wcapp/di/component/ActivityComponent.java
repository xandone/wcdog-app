package com.xandone.dog.wcapp.di.component;

import com.xandone.dog.wcapp.di.module.ActivityModule;
import com.xandone.dog.wcapp.di.scope.ActivityScope;
import com.xandone.dog.wcapp.ui.comment.JokeCommentActivity;
import com.xandone.dog.wcapp.ui.jokedetails.JokeDetailsActivity;
import com.xandone.dog.wcapp.ui.login.LoginActivity;
import com.xandone.dog.wcapp.ui.personal.PersonalFragment;
import com.xandone.dog.wcapp.ui.regist.RegistActivity;
import com.xandone.dog.wcapp.ui.splash.SplashAvtivity;

import dagger.Component;

/**
 * author: xandone
 * created on: 2019/3/6 9:46
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);

    void inject(SplashAvtivity splashAvtivity);

    void inject(RegistActivity registActivity);

    void inject(JokeDetailsActivity jokeDetailsActivity);

    void inject(JokeCommentActivity jokeCommentActivity);

}
