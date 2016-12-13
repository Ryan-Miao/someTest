package com.test.java.lambda.entity;

/**
 * Created by rmiao on 12/8/2016.
 */
public class User {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }
}
