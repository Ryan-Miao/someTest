package com.test.lombok;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.junit.Assert;

import java.util.Date;
import java.util.Set;

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
    @Singular
    private Set<String> occupations;

    public static void main(String[] args) {
        Room room = Room.builder().active(true)
                .name("name")
                .id("id")
                .createTime(new Date())
                .occupation("1")
                .occupation("2")
                .build();

        Assert.assertEquals(2, room.getOccupations().size());

    }
}
