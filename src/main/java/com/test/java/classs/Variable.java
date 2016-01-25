package com.test.java.classs;

/**
 * Created by Administrator on 2015/12/7.
 * 在类的内部，变量定义的顺序决定了初始化的顺序。
 * 变量会在任何方法（包含构造器）被调用之前得到初始化
 */
public class Variable {
    public static void main(String[] args) {
        House house = new House();
        house.f();
    }

}

class Window{
    public Window(int n) {
        System.out.println("window-"+n);
    }
}

class House{
    //1
    Window w1 = new Window(1);

    public House() {
        //4
        System.out.println("house()");
        //5
        w4 = new Window(44);
        //6
        Window w2 = new Window(2);
    }
    //2
    Window w3 = new Window(3);
    void f(){
        System.out.println("f()");
    }
    //3
    Window w4 = new Window(4);

}

