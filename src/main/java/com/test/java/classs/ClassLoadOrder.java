package com.test.java.classs;

/**
 * Created by Administrator on 2015/12/8.
 * 类的加载顺序
 * 1加载器启动找到 xxx.class文件，通过extends关键字寻找基类，先加载基类
 * 2类初始化先初始化static成员变量和static--->
 * 2先初始化父类的成员变量和static
 * 3再初始化本类的成员变量和static
 * 类加载之后，对象创建开始加载
 * 1先加载父类的非静态成员变量（静态成员变量在类初始化的时候已经加载，非静态成员变量要随对象的创建而初始化）
 * 2先加载父类的构造函数
 * 3再加载本类的非静态成员变量
 * 4再加载本类的构造函数
 *
 * 总体：
 * -->表示顺序
 * 父类-->子类
 * 静态-->非静态
 * 类-->对象
 * static随类的加载而加载
 * 非static成员变量随对象的创建而加载
 * 成员变量先于构造器加载
 *
 */
public class ClassLoadOrder extends Father{
    //2父类的static成员变量加载完之后 开始加载子类的static域
    private static int k = printInt("child static k initialized");
    //5 子类的非静态成员变量初始化
    private int m = printInt("child 非static 变量加载");

    //子类的构造器加载
    public ClassLoadOrder() {
        System.out.println("child constructor initialized");
        System.out.println("k="+k);
        System.out.println("j="+j);
    }

    static {
        System.out.println("child static initialized");
    }
    static int printInt2(){
        System.out.println("child static function initialized");
        return 50;
    }

    public static void main(String[] args) {
        ClassLoadOrder c = new ClassLoadOrder();
        System.out.println(c.k);
    }
}

class Father{
    private int i=9;
    protected int j;
    //4 父类构造器加载
    Father(){
        System.out.println("father constructor initialized");
        System.out.println("i="+i+",j="+j);
        j=39;
    }
    //3 对象创建时，先初始化父类的非静态成员变量
    int n = printInt("father 非static变量加载");
    //1先加载父类的static域
    static {
        System.out.println("father static initialized");
    }
    //1
    private static int x1 = printInt("father static .x1 initialized");
    static int printInt(String s ){
        System.out.println(s);
        return 47;
    }
}
