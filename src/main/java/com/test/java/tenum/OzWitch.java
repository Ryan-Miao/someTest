package com.test.java.tenum;

/**
 * 可以在enum中添加方法和构造器
 * Created by Administrator on 2016/3/30.
 */
public enum OzWitch {
    WEST("Miss Gulch, aka the Wiched Witch of the West"),
    NORTH("Glinda,the Good Witch of the North"),
    EAST("Wicked Witch of the East,wearer of the Ruby Slippers,crushed by Dorothy's house"),
    SOUTH("Good by inference,but missing")
    ;

    private String description;
    private OzWitch(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    public static void main(String[] args) {
        for (OzWitch with :
                OzWitch.values()) {
            System.out.println(with+":"+with.getDescription());
        }
    }
}
