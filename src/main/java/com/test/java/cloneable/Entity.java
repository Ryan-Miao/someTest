package com.test.java.cloneable;

/**
 * Created by Ryan Miao on 11/16/17.
 */
public class Entity implements Cloneable {
    private String id;
    private String name;

    public Entity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
