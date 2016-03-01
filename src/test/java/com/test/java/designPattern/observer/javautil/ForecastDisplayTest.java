package com.test.java.designPattern.observer.javautil;

import static org.junit.Assert.*;

/**
 * 测试拉取数据
 * Created by mrf on 2016/3/1.
 */
public class ForecastDisplayTest {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay =
                new CurrentConditionsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        //拉取
        forecastDisplay.update(weatherData,forecastDisplay);
        currentDisplay.update(weatherData,forecastDisplay);
        weatherData.setMeasurements(82, 70, 29.2f);
        forecastDisplay.update(weatherData,forecastDisplay);
        weatherData.setMeasurements(78, 90, 39.2f);
        forecastDisplay.update(weatherData,forecastDisplay);
    }
}