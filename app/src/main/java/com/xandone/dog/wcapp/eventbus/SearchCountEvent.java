package com.xandone.dog.wcapp.eventbus;

/**
 * author: Admin
 * created on: 2019/6/27 14:41
 * description:
 */
public class SearchCountEvent {
    private int count;

    public SearchCountEvent(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
