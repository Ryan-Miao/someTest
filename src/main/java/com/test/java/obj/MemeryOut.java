package com.test.java.obj;

/**
 * Created by Administrator on 2015/12/3.
 * 关于内存垃圾回收
 */
public class MemeryOut {
    public MemeryOut() {
    }

    public static void main(String[] args) throws InterruptedException {
        Book novel = new Book(true);//借走
        novel.checkIn();

        new Book(true);
        Thread.sleep(1000);
        System.gc();
    }
}

class Book{
    boolean checkedOut = false;
    Book(boolean checkOut){
        checkedOut = checkOut;
    }
    void checkIn(){
        checkedOut=false;
    }
    protected void finalize() throws Throwable {

        System.out.println("进入垃圾回收前调用此方法");
        if(checkedOut){
            System.out.println("Error: checked out");
        }

        super.finalize();
    }
}
