package com.test.java.designPattern.factory.factoryMethod;

/**
 * 邮件发送
 * Created by mrf on 2016/2/25.
 */
public class MailSender implements Sender {
    @Override
    public String send() {
        System.out.println("This is emailSender!");
        return "email";
    }
}
