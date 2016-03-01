package com.test.java.designPattern.observer.javautil;

import com.test.java.designPattern.observer.DisplayElement;
import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式
 * 角色：观察者
 * 通过实现java内置的方法实现观察功能
 * Created by mrf on 2016/3/1.
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    Observable observable;
    private float temperature;
    private float humidity;
    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData)obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
    public void display() {
        System.out.println("Current conditions: " + temperature
                + "F degrees and " + humidity + "% humidity");
    }
}
