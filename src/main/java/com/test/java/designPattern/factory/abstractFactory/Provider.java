package com.test.java.designPattern.factory.abstractFactory;

import com.test.java.designPattern.factory.factoryMethod.Sender;

/**
 * 抽象工厂模式
 * 消息发送提供者
 * Created by mrf on 2016/2/26.
 */
public interface Provider {
    public Sender produce();
}
