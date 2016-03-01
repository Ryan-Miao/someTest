package com.test.java.designPattern.observer.javautil;

import java.util.Observable;

/**
 * 观察者模式
 * 角色：主题
 * 通过继承java内置的对象来实现
 * Created by mrf on 2016/3/1.
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;
    public WeatherData() { }
    public void measurementsChanged() {
        setChanged();//设置通知标识
        notifyObservers();//通知
    }
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
    public float getTemperature() {
        return temperature;
    }
    public float getHumidity() {
        return humidity;
    }
    public float getPressure() {
        return pressure;
    }
}
