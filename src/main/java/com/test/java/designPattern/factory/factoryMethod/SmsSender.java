package com.test.java.designPattern.factory.factoryMethod;

/**
 * 短信发送
 * Created by mrf on 2016/2/25.
 */
public class SmsSender implements Sender {
    @Override
    public String send() {
        System.out.println("This is SmsSender!!");
        return "sms";
    }
}
