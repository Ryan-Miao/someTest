package com.test.java.designPattern.factory.abstractFactory;

import com.test.java.designPattern.factory.factoryMethod.MailSender;
import com.test.java.designPattern.factory.factoryMethod.Sender;

/**
 * Created by mrf on 2016/2/26.
 */
public class MailSendFactory implements Provider {
    public String a;
    public MailSendFactory(String a) {
        this.a = a;
    }

    @Override
    public Sender produce() {
        return new MailSender();
    }

    public void test(){
        System.out.println(a);
    }
}
