package com.test.java.designPattern.factory.abstractFactory;

import com.test.java.designPattern.factory.factoryMethod.MailSender;
import com.test.java.designPattern.factory.factoryMethod.Sender;
import com.test.java.designPattern.factory.factoryMethod.SmsSender;

/**
 * Created by mrf on 2016/2/26.
 */
public class SmsSendFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
