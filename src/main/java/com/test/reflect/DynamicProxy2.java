package com.test.reflect;

/**
 * Created by mrf on 2015/11/26.
 * 测试动态代理
 * Subject  抽象类  抽象角色 定义一个抽象方法request
    RealSubject  真实角色  继承了抽象类Subject 实现抽象方法request
     ProxySubject  代理角色  同样继承抽象类Subject实现抽象方法request
 */
public class DynamicProxy2 {

    //客户端调用
    public static void main(String[] args) {
        Subject2 sub = new ProxySubject();
        sub.request();
    }

}
//抽象角色
abstract class  Subject2{
    abstract public void request();
}

//真实角色
 class RealSubject2 extends Subject2{
    public RealSubject2(){}
    @Override
    public void request() {
        System.out.println("局长办事了！");
    }
}

//代理角色
class ProxySubject extends Subject2{
private RealSubject2 realSubject2;//以真实角色 做为代理的属性
    public ProxySubject(){}
    //该方法封装了真实对象的request方法
    public void request() {
        preRequest();
        if(realSubject2==null){
            realSubject2=new RealSubject2();
        }
        realSubject2.request();//此处执行真实对象的request方法
        postRequest();
    }

    private void postRequest() {
        System.out.println("秘书回来了！");
    }

    private void preRequest() {
        System.out.println("秘书找局长");
    }
}





