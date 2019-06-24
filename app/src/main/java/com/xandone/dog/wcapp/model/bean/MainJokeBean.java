package com.xandone.dog.wcapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author: xandone
 * created on: 2018/11/26 23:26
 */
public class MainJokeBean implements Serializable {
    private JokeBean jokeBean;
    private List<HeadArticleBean> articleList;

    public MainJokeBean(JokeBean jokeBean) {
        this.jokeBean = jokeBean;
    }

    public MainJokeBean(List<HeadArticleBean> articleList) {
        this.articleList = articleList;
    }


    public JokeBean getJokeBean() {
        return jokeBean;
    }

    public void setJokeBean(JokeBean jokeBean) {
        this.jokeBean = jokeBean;
    }

    public List<HeadArticleBean> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<HeadArticleBean> articleList) {
        this.articleList = articleList;
    }
}
