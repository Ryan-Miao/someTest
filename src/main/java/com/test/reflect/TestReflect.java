package com.test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2015/11/21.
 */
public class TestReflect {
    public static void main(String[] args) {
        Test test = new Test();
        Class<? extends Test> aClass = test.getClass();
        //获取类名
        String name = aClass.getName();
        System.out.println(name);
        System.out.println("========分割线===============================================");
        Class<?> test1 = null;
        Class<?> test2 = null;
        Class<?> test3 = null;
        try {
            test1 = Class.forName("com.test.reflect.Test");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        test2 = new Test().getClass();
        test3 = Test.class;

        System.out.println(test1.getName());
        System.out.println(test2.getName());
        System.out.println(test3.getName());

        System.out.println("=================================分割线=============================================");
        Class<?> demo = null;
        try {
            demo=Class.forName("com.test.reflect.Test");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Test person = null;
        //通过class实例化其他类的对象
        try {
            person = (Test) demo.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        person.setName("人");
        person.setAge(20);
        System.out.println(person);
        System.out.println(demo);

        System.out.println("=================================分割线=============================================");
        //通过Class调用其他类的构造函数
        Test per1 = null;
        Test per2 = null;
        Test per3 = null;
        Test per4 = null;
        //取得全部的构造函数
        Constructor<?>[] constructors = demo.getConstructors();
        for (Constructor<?> constructor : constructors) {
            try {
                constructor.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

}

class Test{
    private  String name;
    private  int age;

    /**
     * 无参构造函数必须要有，否则，反射出问题
     */
    public Test() {
    }

    public Test(String name) {
        this.name = name;
    }

    public Test(int age) {
        this.age = age;
    }

    public Test(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
