package com.test.java.tenum;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * 通过反射查看enum的values
 * 结果：values是编译器添加到enum类的，而Enum类本身没有values方法
 * Created by Administrator on 2016/3/30.
 */
public class Reflection {
    public static Set<String> analyze(Class<?> enumClass){
        System.out.println("-----------Analyzing "+enumClass+"-------");
        System.out.println("Interfaces:");
        for (Type t :
                enumClass.getGenericInterfaces()) {
            System.out.println(t);
        }
        System.out.println("Base: "+enumClass.getSuperclass());
        System.out.println("Method:");
        Set<String> methods = new TreeSet<>();
        for (Method m :
                enumClass.getMethods()) {
            methods.add(m.getName());
        }
        System.out.println(methods);
        return methods;
    }
    public static void main(String[] args){
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println("Explore.containsAll(Enum)?"+enumMethods.containsAll(enumMethods));
        System.out.println("Explore.removeAll(Enum):");
        exploreMethods.removeAll(enumMethods);
        System.out.println(exploreMethods);

    }
}
enum Explore{
    HERE,THERE
}
/************************通过class获取实例********************************/
enum Search{
    HITHER,YON
}
class UpcastEnum{
    public static void main(String[] args) {
        Search[] values = Search.values();
        Enum e = Search.HITHER;
//        e.values();//编译报错，说明No values() in Enum
        for (Enum anEnum : e.getClass().getEnumConstants()) {
            System.out.println(anEnum);
        }
    }
}

class NonEnum{
    public static void main(String[] args) {
        Class<Integer> integerClass = Integer.class;
        try{
            for (Object en : integerClass.getEnumConstants()) {
                System.out.println(en);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
