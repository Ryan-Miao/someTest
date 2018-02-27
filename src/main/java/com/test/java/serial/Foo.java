package com.test.java.serial;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Ryan Miao
 */
@Data
@Builder
public class Foo implements Serializable {

    private static final String LOGGER = "logger";
    public static final String PUB_STATIC_FINAL = "publicStaticFinal";
    private static final long serialVersionUID = 3963846241085163310L;
    public static String PUB_STATIC;

    public String fa;
    private String fb;
    transient public String ta;
    transient private String tb;
}
