package com.test.java8.c2;

/**
 * Created by Ryan on 2017/07/15/0015.
 */
public class MeaningOfThis {
    public final int value = 4;

    public void doInt(){
        int value = 5;
        Runnable r = new Runnable() {
            public final int value = 6;
            @Override
            public void run() {
                int value = 10;
                System.out.println("value="+value);
                System.out.println("this.value="+this.value);
            }
        };

        r.run();
    }

    public static void main(String[] args) {
        MeaningOfThis meaningOfThis = new MeaningOfThis();
        meaningOfThis.doInt();
    }
}
