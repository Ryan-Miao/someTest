package com.test.java.designPattern.observer;

/**
 * 主题：天气变化管理接口
 * Created by mrf on 2016/3/1.
 */
public interface Subject {
    //注册观察者
    public void registerObserver(Observer o);
    //移除观察者
    public void removeObserver(Observer o);
    //当天气改变时，这个方法会被调用，以通知所有的观察者
    public void notifyObservers();
}
