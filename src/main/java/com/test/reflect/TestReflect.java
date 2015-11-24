package com.test.reflect;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.lang.reflect.*;

/**
 * Created by Administrator on 2015/11/21.
 */
public class TestReflect {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
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
        System.out.println("================通过Class调用其他类的构造函数===================");
        //取得全部的构造函数
        Constructor<?>[] cons = demo.getConstructors();
        try{
            System.out.println(cons[0].newInstance("呵呵",1));
            System.out.println(cons[1].newInstance(2));
            System.out.println(cons[2].newInstance("顺序"));
            System.out.println(cons[3].newInstance());
            for (int i = 0; i < cons.length; i++) {
                Class<?>[] p = cons[i].getParameterTypes();
                System.out.println("构造方法： ");
                int mo = cons[i].getModifiers();
                System.out.println(Modifier.toString(mo));
                System.out.println(cons[i].getName());
                System.out.println("(");
                for (int j = 0; j < p.length; j++) {
                    System.out.println(p[j].getName()+" arg"+i);
                    if(j<p.length-1){
                        System.out.println(",");
                    }
                }
                System.out.println("){}");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("=================================分割线=============================================");
        //获取类的全部属性
        System.out.println("====================本类属性=====================");
        Field[] fields = demo.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            //权限修饰符
            int mo = fields[i].getModifiers();
            String priv = Modifier.toString(mo);
            //属性类型
            Class<?> type = fields[i].getType();
            System.out.println(priv+" " + type.getName()+" "+fields[i].getName()+";");
        }
        System.out.println("====================父类或接口属性=====================");
        Field[] fields1 = demo.getFields();
        for (int j = 0; j < fields1.length; j++) {
            //权限修饰符
            int mo = fields1[j].getModifiers();
            String priv = Modifier.toString(mo);
            //属性类型
            Class<?> type = fields1[j].getType();
            System.out.println(priv+" " + type.getName()+" "+fields1[j].getName()+";");
        }

        System.out.println("=================================分割线=============================================");
        System.out.println("===============调用方法==================");
        try {
            Method sayHello = demo.getMethod("sayHello");
            Test t = (Test)demo.newInstance();
            t.setName("类实例对象调用方法");
            sayHello.invoke(t);
            Method sayChina = demo.getMethod("sayChina", String.class);
            sayChina.invoke(t, "河北省");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("=================================分割线=============================================");
        System.out.println("===============操作属性==================");
        Object o = demo.newInstance();
        Field sex = demo.getDeclaredField("name");
        sex.setAccessible(true);
        sex.set(o, "男");
        System.out.println(sex.get(o));
        System.out.println("=================================分割线=============================================");
        System.out.println("==============操作数组，修改================");
        int[] temp = {1,2,3,4};
        Class<?> arr = temp.getClass().getComponentType();
        System.out.println("数组类型："+arr.getName());
        System.out.println("数组长度"+Array.getLength(temp));
        System.out.println("数组的第一个元素"+Array.get(temp, 0));
        Array.set(temp, 0, 100);
        System.out.println("修改之后数组第一个元素："+Array.get(temp,0));

        System.out.println("=================================分割线=============================================");



    }

}

class Test{
    private  String name;
    private  int age;

    /**
     * 无参构造函数必须要有，否则，反射构造无参出错
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

    public void sayHello(){
        System.out.println("My name is "+name);
    }

    public void sayChina(String province){
        System.out.println("im am from " + province);
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
