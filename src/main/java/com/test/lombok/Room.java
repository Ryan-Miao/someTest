package com.test.lombok;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

/**
 * Created by Ryan Miao on 1/18/18.
 */
@Data
@Builder(toBuilder = true)
public class Room {
    @NonNull
    private String id;
    private String name;
    private boolean active;
    private Date createTime;

    public static void main(String[] args) {
        Room room = Room.builder().active(true)
                .name("name")
                .id("id")
                .createTime(new Date())
                .build();


        room.getCreateTime();
        room.setActive(false);
        room.setId("id");
        room.getId();

    }
}
