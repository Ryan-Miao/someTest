package com.test.reflect;

import java.io.*;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/11/25.
 * 对象复制
 */
public class ObjectCopy {

    public static void main(String[] args) throws Exception {
        A baseObject = new A(new B(new C("bString1", "bString2"), 1, 2), new C(
                "cString1", "cString2"));
        A copyObject = (A) copyObject(baseObject, "java.lang.Integer",
                "java.lang.String");

        System.out.println(baseObject);
        System.out.println(copyObject);
        System.out.println("===================分割线===================");
        System.out.println("原对象修改前："+baseObject);
        A a = (A)cloneBySer(baseObject);
        System.out.println("复制对象     ："+a);
        a.setC(new C("cchange1","cchange2"));
        System.out.println("复制后修改对象："+a);
        System.out.println("原对象修改后  ："+baseObject);

    }

    /**
     * COPY对象(毛病还是很多的。。)
     *对基本类型的过滤
     * @author Lv9
     * @since 2010.03.09
     * baseObject 要拷贝的对象
     * noCopyClassNames 不深度拷贝的对象属性
     */
    public static Object copyObject(Object baseObject,
                                    String... noCopyClassNames) throws Exception {
        Object copyObject = baseObject.getClass().newInstance();

        Field[] fields = baseObject.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (checkClassType(field.getType().getName(), noCopyClassNames)) {
                field.set(copyObject, field.get(baseObject));
            } else {
                field.set(copyObject, copyObject(field.get(baseObject),
                        noCopyClassNames));
            }
        }
        return copyObject;
    }

    public static boolean checkClassType(String className,
                                         String[] noCopyClassNames) {
        if(className.equals("")){

        }
        for (String noCopyClassName : noCopyClassNames) {
            if (className.equals(noCopyClassName)) {
                return true;
            }
        }
        return false;
    }

    // 用序列化与反序列化实现深克隆
    public static Object cloneBySer(Object baseObj) {
        Object o = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(baseObj);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos
                    .toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            o = ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }
}

class A implements Serializable{
    private B b;
    private C c;

    public A() {

    }

    public A(B b, C c) {
        this.b = b;
        this.c = c;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }


    @Override
    public String toString() {
        return "A{" +
                " b=" + b +
                ", c=" + c +
                '}';
    }
}

class B implements Serializable{
    private C c;
    private Integer int1;
    private Integer int2;

    public B() {

    }

    public B(C c, Integer int1, Integer int2) {
        this.c = c;
        this.int1 = int1;
        this.int2 = int2;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public Integer getInt1() {
        return int1;
    }

    public void setInt1(Integer int1) {
        this.int1 = int1;
    }

    public Integer getInt2() {
        return int2;
    }

    public void setInt2(Integer int2) {
        this.int2 = int2;
    }

    @Override
    public String toString() {
        return "[B: int1 = " + int1 + ",int2 = " + int2 + ",c = " + c
                + ",hashCode = " + hashCode() + "]";
    }
}

class C implements Serializable{
    private String string1;
    private String string2;

    public C() {

    }

    public C(String string1, String string2) {
        this.string1 = string1;
        this.string2 = string2;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    @Override
    public String toString() {
        return "[C: string1 = " + string1 + ",string2 = " + string2
                + ",hashCode = " + hashCode() + "]";
    }
}