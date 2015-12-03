package com.test.java.obj;

/**
 * Created by Administrator on 2015/12/3.
 * 类的static变量对于所有对象共享，共同指向同一个内存区域
 * 非static字段则是对每个对象有一个存储空间
 */
public class TestObj {

    private static int i=47;

    public TestObj() {
    }


    public static void main(String[] args) {
        TestObj obj = new TestObj();
        TestObj obj2 = new TestObj();
        System.out.println("初始化：");
        System.out.println(obj.i);
        System.out.println(obj2.i);

        System.out.println("++");
        obj.i++;
        System.out.println(obj.i);
        System.out.println(obj2.i);

    }

}
