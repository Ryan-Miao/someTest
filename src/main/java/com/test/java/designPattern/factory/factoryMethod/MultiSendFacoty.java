package com.test.java.designPattern.factory.factoryMethod;

/**
 * 多个工厂模式
 * <p>
 *     是对普通工厂方法模式的改进。因为普通方法模式中key错误则不能正确创建对象，
 *     多个工厂模式提供多个创建方法。
 * </p>
 * Created by mrf on 2016/2/26.
 */
public class MultiSendFacoty {

    public Sender produceMail(){
        return  new MailSender();
    }

    public Sender produceSms(){
        return  new SmsSender();
    }
}
