package com.test.java.classs;

/**
 * Created by Administrator on 2015/12/7.
 * 练习测试继承
 * Java中子类能够继承父类的private属性或方法吗？
 　　书中看到：
 　　子类继承父类，子类拥有了父类的所有属性和方法。
 　　程序验证，父类的私有属性和方法子类是无法直接访问的。当然私有属性可以通过public修饰的getter和setter方法访问到的，但是私有方法不行。
 　　假设：子类不能够继承父类的私有属性和方法
 　　那么：分析内存后，会发现，当一个子类被实例化的时候，默认会先调用父类的构造方法对父类进行初始化，即在内存中创建一个父类对象，然后再父类对象的外部放上子类独有的属性，两者合起来成为一个子类的对象。
 　　所以：子类继承了父类的所有属性和方法或子类拥有父类的所有属性和方法是对的，只不过父类的私有属性和方法，子类是无法直接访问到的。即只是拥有，但是无法使用。
 */
public class Parent {

    public String a="p-a";
    /**同一个包可以，子类可以，非子类不同包不可以**/
    protected String b  = "p-b";
    /**同一个包可以使用，同包的子类可以，不同包的子类不可以**/
    String  d = "p-d";
    /**只有类内部调用，外部不可以**/
    private String c = "p-c";

    public void publicFunc(){
        System.out.println("this is parent pubFun ");
    }

    protected void protectFunc(){
        System.out.println("this is parent proFun");
    }

    void friendFunc(){
        System.out.println("this is parent friFun");
    }

    private void privateFun(){
        System.out.println("this is parent priFun");
    }

    /**
     * 获取自身属性
     */
    public void getFiled(){
        System.out.print(a+" ");
        System.out.print(b+" ");
        System.out.print(d+" ");
        System.out.println(c+" ");
    }

    public static void main(String[] args) {
        Parent parent = new Parent();
        System.out.println(parent.c);
    }


}

class Children extends Parent{

    public String a="c-a";
    /**同一个包可以，子类可以，非子类不同包不可以**/
    protected String b  = "c-b";
    /**同一个包可以使用，同包的子类可以，不同包的子类不可以**/
    String  d = "c-d";
    /**只有类内部调用，外部不可以
     *  父类的private的属性会被隐藏，所以这里不是重写覆盖，而是子类自己的属性。
     */
    private String c = "c-c";

    public String newf = "c-f";

    /**
     * 获取自身属性
     */
    public void getFiled(){
        System.out.print(a+" ");
        System.out.print(b+" ");
        System.out.print(d+" ");
        System.out.print(c+" ");
        System.out.println(newf+" ");
    }

    /**
     * 子类自己的方法
     */
    public void newFun(){
        System.out.println("child self new function");
    }

    public static void main(String[] args) {
        Children c = new Children();
        Parent p = new Children();
        Parent parent = new Parent();
        //通过上转型对象，父类只可以调用自己被覆盖的属性,私有属性不能被覆盖，上转型对象也就无法调用
//        System.out.println(p.c);
        //父类对象在父类内部可以调用私有属性，但在其他类中不可以,也就是说私有只能在本类的中使用
//        System.out.println(parent.c);
        System.out.println("=========pub方法被覆盖,方法中的成员变量来自子类,上转型对象只能调用自己被覆盖的方法，该方法中可以调用子类的属性===========");
        System.out.println("子类");
        c.getFiled();
        System.out.println("上转型父类");
        p.getFiled();
        System.out.println("父类");
        parent.getFiled();

        System.out.println("=========属性:私有属性无法访问，其他属性来自自身，上转型对象的属性来自本身而不是子类，上转型对象不能方法子类的新属性===============");
        System.out.println(c.a+" "+c.b+" "+c.d+" "+c.c+" "+c.newf);
        System.out.println(p.a+" "+p.b+" "+p.d);
    }
}
