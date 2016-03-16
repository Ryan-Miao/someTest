package com.test.java;

import java.io.ObjectInput;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by mrf on 2016/3/16.
 */
public class TestInherit {

}
//接口支持多extends
interface Te extends Serializable,ObjectInput,ParameterizedType{

}

class Base {
    String a = "父类a";
    String  b = "父类b";
    static void a() {
        System.out.println("父类的静态A");
    }

    void b() {
        System.out.println("父类的B"+b);
    }

    void c(){
        System.out.println("父类的成员变量a:"+a);
    }

    void d(){
        System.out.println("父成员变量b:"+b);
    }
}

class Inherit extends Base {
    String a = "zi类a";
    String  b = "zi类b";
    static void a() {
        System.out.println("子类的静态C");
    }

    void b() {
        System.out.println("子类b:"+b);
    }
    void c(){
        System.out.println("子类的成员变量a:"+a);
    }


    public static void main(String args[]) {
        Base b = new Base();
        Inherit inherit = new Inherit();
        Base c = new Inherit();
        System.out.println("父类===================");
        b.a();//父类的静态A
        b.b();//父类的B
        System.out.println("子类===================");
        inherit.a();//子类掩盖了父类的静态方法,子类的静态C
        inherit.b();//子类b:zi类b,打印自己的
        inherit.c();//子类的成员变量a:zi类a,打印自己的
        inherit.d();//父成员变量b:父类b,调用父类的d方法，并且d方法里的成员变量a也是b的
        System.out.println("父类指向子类============");
        c.a();//父类的静态A
        c.b();//子类b:zi类b
        c.c();//子类的成员变量a:zi类a
        c.d();//父成员变量b:父类b
    }
}
