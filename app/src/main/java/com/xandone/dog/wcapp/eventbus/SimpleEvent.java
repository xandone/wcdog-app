package com.xandone.dog.wcapp.eventbus;

/**
 * author: Admin
 * created on: 2019/6/25 12:00
 * description:
 */
public class SimpleEvent {
    public static final int KEY_INIT_USER = 1;
    public static final int KEY_CLEAR_USER = 2;

    private int key;

    public SimpleEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
