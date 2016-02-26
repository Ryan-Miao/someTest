package com.test.java.designPattern.factory.factoryMethod;

/**
 * 测试发送消息
 * Created by mrf on 2016/2/25.
 */
public class FactoryTest {

    public static void main(String[] args) {
        SendFactory sendFactory = new SendFactory();
        Sender sender = sendFactory.produce("email");
        sender.send();
    }
}
