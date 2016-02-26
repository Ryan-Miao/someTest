package com.test.java.designPattern.factory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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