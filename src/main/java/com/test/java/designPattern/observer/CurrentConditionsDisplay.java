package com.test.java.designPattern.observer;

/**
 * 显示天气信息的布告板
 * 角色:观察者，订阅者
 * 实现Observer接口以从WeatherData中获取天气信息
 * 实现DisplayElement以显示信息
 * Created by mrf on 2016/3/1.
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;
    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }
    public void display() {
        System.out.println("Current conditions: " + temperature
                + "F degrees and " + humidity + "% humidity and "+pressure+" pressure");
    }
}
