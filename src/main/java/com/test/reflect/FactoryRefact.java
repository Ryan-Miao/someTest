package com.test.reflect;

import java.io.*;
import java.util.Properties;

/**
 * Created by Administrator on 2015/11/24.
 * 通过反射代理进行工厂模式
 */
public class FactoryRefact {
    public static void main(String[] args) {
        //水果工厂模式
        Fruit orange = FruitFactory.getInstance("orange");
        orange.eat();

        //利用反射构建工厂
        try {
            Fruit instance = FruitFactoryByReflect.getInstance("com.test.reflect.Apple");
            if(instance!=null){
                System.out.println("==============反射工厂================");
                instance.eat();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        //结合属性文件
        try {
            Properties property = PropertyInit.getProperty("testreflect.properties");
            Fruit instance = FruitFactoryByReflect.getInstance(property.getProperty("apple"));
            if(instance!=null){
                System.out.println("==============反射工厂结合属性文件================");
                instance.eat();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

interface Fruit{
    public abstract  void eat();
}

class Apple implements Fruit{

    @Override
    public void eat() {
        System.out.println("this is apple object");
    }
}
class Orange implements Fruit{

    @Override
    public void eat() {
        System.out.println("this is orange object");
    }
}

class FruitFactory{
    public static Fruit getInstance(String fruitName){
        Fruit fruit = null;
        if("apple".equals(fruitName)){
            fruit = new Apple();
        }
        if("orange".equals(fruitName)){
            fruit = new Orange();
        }
        return fruit;
    }
}

class FruitFactoryByReflect{
    public static Fruit getInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Fruit fruit = null;
        fruit = (Fruit) Class.forName(className).newInstance();

        return fruit;
    }
}

/**
 * 属性文件加载
 */
class PropertyInit{
    public static Properties getProperty(String filepath) throws IOException {
        Properties p = new Properties();
        try {
            InputStream inputStream = PropertyInit.class.getClassLoader().getResourceAsStream(filepath);
            p.load(inputStream);
        } catch (IOException e1) {
            p.setProperty("apple", "Reflect.Apple");
            p.setProperty("orange", "Reflect.Orange");
            p.store(new FileOutputStream(new File(filepath)), "FRUIT CLASS");
            e1.printStackTrace();
        }

        return p;
    }
}
