package com.test.java.designPattern.factory.factoryMethod;

/**
 * 静态工厂模式
 * <p>
 *     将多个工厂模式中的工厂改为静态的，可以直接用类名创建。
 * </p>
 * Created by mrf on 2016/2/26.
 */
public class StaticSendFacoty {

    public static Sender produceMail(){
        return  new MailSender();
    }

    public static Sender produceSms(){
        return  new SmsSender();
    }
}
