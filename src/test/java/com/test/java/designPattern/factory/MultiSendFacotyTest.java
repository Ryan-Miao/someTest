package com.test.java.designPattern.factory;

import com.test.java.designPattern.factory.factoryMethod.MultiSendFacoty;
import com.test.java.designPattern.factory.factoryMethod.Sender;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mrf on 2016/2/26.
 */
public class MultiSendFacotyTest {
    private MultiSendFacoty facoty;

    @Before
    public void setUp(){
        facoty = new MultiSendFacoty();
    }

    @Test
    public void testProduceMail() throws Exception {
        Sender sender = facoty.produceMail();
        sender.send();

    }

    @Test
    public void testProduceSms() throws Exception {
        Sender sender = facoty.produceSms();
        sender.send();
    }
}