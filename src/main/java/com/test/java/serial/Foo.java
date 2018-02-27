package com.test.java.serial;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Foo implements Serializable {

    private static final String LOGGER = "logger";
    public static final String PUB_STATIC_FINAL = "publicStaticFinal";
    public static String PUB_STATIC = "publicStatic";

    public String fa;
    private String fb;
    transient public String ta;
    transient private String tb;
}
