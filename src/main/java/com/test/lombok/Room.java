package com.test.lombok;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Created by Ryan Miao on 1/18/18.
 */
@Data
@Builder
public class Room {
    private String id;
    private String name;
    private boolean active;
    private Date createTime;
}
