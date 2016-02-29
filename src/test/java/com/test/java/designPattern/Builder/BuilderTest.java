package com.test.java.designPattern.Builder;

import com.test.java.designPattern.factory.factoryMethod.Sender;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mrf on 2016/2/29.
 */
public class BuilderTest {

    @Test
    public void testProduceMailSender() throws Exception {
        Builder builder = new Builder();
        List<Sender> senders = builder.produceMailSender(10);
        Assert.assertNotNull(senders);
        System.out.println(senders);

    }

    @Test
    public void testProduceSmsSender() throws Exception {

    }
}