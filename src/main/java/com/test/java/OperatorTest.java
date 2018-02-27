package com.test.java;

import org.junit.Test;

public class OperatorTest {

    @Test
    public void testAnd(){
        System.out.println(1&2);
        System.out.println(2&3);

        System.out.println(1|2);
        System.out.println(2|3);


        System.out.println(~1);
        System.out.println(~2);

        System.out.println(1^2);
        System.out.println(2^3);

        System.out.println(1<<2);
        System.out.println(3<<3);

        System.out.println(Integer.toHexString(3));
        System.out.println(Integer.toHexString(-3));


        System.out.println(1>>2);
        System.out.println(13>>2);


        System.out.println(-3>>2);
        System.out.println(Integer.toHexString(-1));

        System.out.println(Integer.toHexString(-3>>>2));
        System.out.println(-3>>>2);
    }
}
