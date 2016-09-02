package com.test.java.exception;

import org.junit.Test;

/**
 * Created by rmiao on 9/1/2016.
 */
public class TestThrow {
    public class TestException extends RuntimeException{
        public TestException() {
        }

        public TestException(String message) {
            super(message);
        }

        public TestException(String message, Throwable cause) {
            super(message, cause);
        }
    }


    @Test
    public void testThrowOne() throws Exception{
        String param = "the param to transfer";
        String msg = "something expect error.";
        throw new TestException(msg, new RuntimeException("runtime error."));
    }

    @Test
    public void testCatchOne() throws Exception{
        try {
            throwError();
        } catch (TestException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThrowOneByOne() throws Exception{
        try {
            throwError();
            System.out.println("If throw exception, this will not be show.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testThrowInCatchWilldo() throws Exception{

        try {
            System.out.println("do somethind");
            throwError();
        } catch (Exception e) {
            throwError();
        }
    }

    @Test
    public void testThrowInCatchAndTryCatch() throws Exception{
        try {
            System.out.println("do somethind");
            throwError();
        } catch (Exception e) {
            try {
                throwError();
            } catch (TestException e1) {
                System.out.println("The error can be catch at a catch region. ");
            }
        }
    }

    private void throwError() throws TestException{
        String param = "the param to transfer";
        String msg = "something expect error.";
        throw new TestException(msg, new RuntimeException("runtime error."));
    }
}
