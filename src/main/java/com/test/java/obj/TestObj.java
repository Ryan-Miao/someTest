package com.test.java.obj;

/**
 * Created by Administrator on 2015/12/3.
 * 类的static变量对于所有对象共享，共同指向同一个内存区域
 * 非static字段则是对每个对象有一个存储空间
 */
public class TestObj {

    private static int i=47;
    private int j;

    public TestObj() {
    }


    public static void main(String[] args) {
        TestObj obj = new TestObj();
        TestObj obj2 = new TestObj();
        System.out.println("初始化：");
        System.out.println("i="+obj.i+",j="+obj.j);
        System.out.println("i="+obj2.i+",j="+obj2.j);

        System.out.println("++");
        obj.i++;
        obj.j++;
        System.out.println("i="+obj.i+",j="+obj.j);
        System.out.println("i="+obj2.i+",j="+obj2.j);

    }

}
