package com.test.java.designPattern.observer.javautil;

import static org.junit.Assert.*;

/**
 * Created by mrf on 2016/3/1.
 */
public class ForecastDisplayTest {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        forecastDisplay.update(weatherData,forecastDisplay);
        weatherData.setMeasurements(82, 70, 29.2f);
        forecastDisplay.update(weatherData,forecastDisplay);
        weatherData.setMeasurements(78, 90, 39.2f);
        forecastDisplay.update(weatherData,forecastDisplay);
    }

}