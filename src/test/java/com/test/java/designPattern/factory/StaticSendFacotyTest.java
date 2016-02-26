package com.test.java.designPattern.factory;

import com.test.java.designPattern.factory.factoryMethod.Sender;
import com.test.java.designPattern.factory.factoryMethod.StaticSendFacoty;
import org.junit.Test;

/**
 * Created by mrf on 2016/2/26.
 */
public class StaticSendFacotyTest {

    @Test
    public void testProduceMail() throws Exception {
        Sender sender = StaticSendFacoty.produceMail();
        sender.send();
    }

    @Test
    public void testProduceSms() throws Exception {
        Sender sender = StaticSendFacoty.produceSms();
        sender.send();
    }
}