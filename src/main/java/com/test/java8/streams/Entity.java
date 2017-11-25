package com.test.java8.streams;

import java.util.Date;

/**
 * Created by Ryan Miao on 11/17/17.
 */
class Entity {
    private int id;
    private Date updateTime;

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", updateTime=" + updateTime +
                '}';
    }
}
