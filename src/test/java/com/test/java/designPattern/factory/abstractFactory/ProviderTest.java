package com.test.java.designPattern.factory.abstractFactory;

import com.test.java.designPattern.factory.factoryMethod.Sender;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mrf on 2016/2/26.
 */
public class ProviderTest {

    private Provider provider;

    @Test
    public void testMailProduce() throws Exception {
        provider = new MailSendFactory("a");
        Sender sender = provider.produce();
        assertEquals("email", sender.send());

        MailSendFactory m = (MailSendFactory)provider;
        m.test();
    }
    @Test
    public void testSmsProduce() throws Exception {
        provider = new SmsSendFactory();
        Sender sender = provider.produce();
        assertEquals("sms", sender.send());
    }
}