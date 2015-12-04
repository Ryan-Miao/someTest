package com.test.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/3.
 * 约瑟夫环
 * n个人一圈，1-m循环报数，剩下的编号？
 */
public class Joseph {

    /**
     * 遍历--数组
     * 时间复杂度O(mn)
     */
    public static void forall(int n, int m) {
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = 0;//初始化
        }
        System.out.println();
        int t = 0;//指针\
        int s = 0;//报数
        int f = 0;//死亡人数
        int count = 0;//计算次数
        while (f != n) {
            count++;
            if (t == n) {//t指向第一个
                t = 0;
            }
            if (a[t] == 0) {//t位置的人存在
                s++;//报数
            }
            if (s == m) {
                s = 0;//清零
                a[t] = 1;//标记死亡
                ++f;//死亡人数++
                if(f==n){
                    System.out.print(t + 1 + " ");//输出
                    break;
                }
            }
            ++t;
        }
        System.out.println();
        System.out.println("计算次数：" + count);
    }

    /**
     * 遍历--ArrayList
     * 这里忽略了list的内部实现，似乎并不好表示复杂度
     */
    public static int forallArrayList(int n,int m) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i+1);
        }
        int len = 0;//移除的个数
        int t=0;//指针
        int s = 0;//报数
        int count=0;//计算次数
        while(len!=n){
            count++;
            s++;//报数
            if(t==(n-len)){
                t=0;
            }
            if(s==m){//若为m
                s=0;//清零
                len++;//移除的个数
                if(len==n){
                    System.out.print(list.get(t)+" ");
                    break;
                }
                list.remove(t);
                //list会调节下一个到本位置，不用指针移动
            }else{
                t++;//指针移到下一个
            }

        }
        System.out.println("\n计算次数："+count);

        return 0;
    }

    /**
     * 数列推导
     * /*
     * If number = 3
     * f(1) = 0
     * f(2) = 1 = (f(1) + 3) % 2
     * f(3) = 1 = (f(2) + 3) % 3
     * f(4) = 0 = (f(3) + 3) % 4
     * f(5) = 3 = (f(4) + 3) % 5
     * ...
     * f(n) = x = (f(n-1) + 3) % n
     *
     */
    public static void sequenceInfer(int n, int m){
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last+m)%i;
        }
        System.out.println(last+1+" ");
    }

    public static void main(String[] args) throws InterruptedException {
        int n =44444441;
        int m = 3;
        long a = System.currentTimeMillis();
        forall(n, m);
        long b = System.currentTimeMillis();
        System.out.println("array用时：" + (b - a));

//        long s = System.currentTimeMillis();
//        forallArrayList(n,m);
//        long e = System.currentTimeMillis();
//        System.out.println("list用时："+(e-s));

        long s1 = System.currentTimeMillis();
        sequenceInfer(n,m);
        long e1 = System.currentTimeMillis();
        System.out.println("数列推导公式用时："+(e1-s1));
        /**维护ArrayList的开销很大啊
         38996
         计算次数：43026596
         array用时：94
         38996
         计算次数：3582300
         list用时：108622
         */

    }
}
