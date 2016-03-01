package com.test.java.designPattern.observer.javautil;

import com.test.java.designPattern.observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by mrf on 2016/3/1.
 */
public class ForecastDisplay implements Observer, DisplayElement {
    Observable observable;
    private float lastPresure;
    private float currentPresure =12.5f;

    public ForecastDisplay(Observable observable) {
        this.observable =observable;
    }

    @Override
    public void display() {
        System.out.println("当前压强："+currentPresure+"; 上一次压强："+lastPresure);
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            lastPresure = currentPresure;
            currentPresure = ((WeatherData) obs).getPressure();
            display();
        }
    }
}
