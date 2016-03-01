package com.test.java.designPattern.observer;

/**
 * 观察者接口
 * Created by mrf on 2016/3/1.
 */
public interface Observer {
    /**
     * 天气更新通知
     * @param temp       气象观测值：温度
     * @param humidity 气象观测值：湿度
     * @param pressure  气象观测值：压强
     */
    public void update(float temp, float humidity, float pressure);
}
