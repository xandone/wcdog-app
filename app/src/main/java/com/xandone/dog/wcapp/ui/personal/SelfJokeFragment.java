package com.xandone.dog.wcapp.ui.personal;

import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseRxFragment;
import com.xandone.dog.wcapp.model.bean.JokeBean;

import java.util.List;

/**
 * author: xandone
 * created on: 2019/6/24 22:19
 */
public class SelfJokeFragment extends BaseRxFragment<SelfJokePresenter> implements SelfJokeContact.MyView {
    @Override
    public int setLayout() {
        return R.layout.frag_self_joke;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter.getJokeList(1, 10, "-1", SelfJokeContact.MODE_ONE);
    }

    @Override
    public void showContent(List<JokeBean> jokeList, int total) {

    }

    @Override
    public void showContentMore(List<JokeBean> jokeList, int total) {

    }
}
