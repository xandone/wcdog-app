package com.xandone.dog.wcapp.eventbus;

/**
 * author: Admin
 * created on: 2019/6/26 14:15
 * description:
 */
public class PersonalEvent {
    private int count;
    private int type;

    public PersonalEvent(int type, int count) {
        this.count = count;
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
