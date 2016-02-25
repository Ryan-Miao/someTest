package com.test.java.designPattern.factory;

/**
 * 发送工厂
 * Created by mrf on 2016/2/25.
 */
public class SendFactory {

    public Sender produce(String type){
        if("email".equals(type)){
            return new MailSender();
        }
        if ("sms".equals(type)){
            return new SmsSender();
        }
        System.out.println("输入类型不正确！");
        return null;
    }
}
