package com.test.java.designPattern.factory;

import com.test.java.designPattern.factory.factoryMethod.SendFactory;
import com.test.java.designPattern.factory.factoryMethod.Sender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mrf on 2016/2/25.
 */
public class SendFactoryTest {

    protected long startTime;
    protected long endTime;

    @Before
    public void setUp() throws Exception {
        this.startTime= System.currentTimeMillis();
        System.out.println("=========开始测试===========");
    }

    @After
    public void tearDown() throws Exception {
        this.endTime = System.currentTimeMillis();
        System.out.println("测试用时："+(endTime-startTime));
        System.out.println("=========测试结束===========");
    }

    @Test
    public void testProduce() throws Exception {
        SendFactory sendFactory = new SendFactory();
        Sender sender = sendFactory.produce("email");
        String send = sender.send();
        assertEquals("email",send);
    }
}