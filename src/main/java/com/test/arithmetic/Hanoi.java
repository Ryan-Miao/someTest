package com.test.arithmetic;

import java.util.Scanner;

/**
 * Created by Administrator on 2015/12/4.
 * 汉诺塔
 * 当只有一个盘子的时候，只需要从将A塔上的一个盘子移到C塔上。
 当A塔上有两个盘子是，先将A塔上的1号盘子（编号从上到下）移动到B塔上，再将A塔上的2号盘子移动的C塔上，最后将B塔上的小盘子移动到C塔上。
 当A塔上有3个盘子时，先将A塔上编号1至2的盘子（共2个）移动到B塔上（需借助C塔），然后将A塔上的3号最大的盘子移动到C塔，最后将B塔上的两个盘子借助A塔移动到C塔上。
 当A塔上有n个盘子是，先将A塔上编号1至n-1的盘子（共n-1个）移动到B塔上（借助C塔），然后将A塔上最大的n号盘子移动到C塔上，最后将B塔上的n-1个盘子借助A塔移动到C塔上。
 综上所述，除了只有一个盘子时不需要借助其他塔外，其余情况均一样（只是事件的复杂程度不一样）。
 */
public class Hanoi {

    static int i;

    public static void move(int n,char from,char to){
        System.out.println("第"+(++i)+"步，将编号"+n+"从"+from+"-->"+to);
    }

    public static void han(int n, char from, char dependon, char to){
        if(n==1){
            move(1,from,to);
        }else {
            han(n-1,from,to,dependon);//将n-1从a 借助c 移动到 b
            move(n,from,to);//将n从a移动到c
            han(n-1,dependon,from,to);//将n-1从b 借助a 移动到 c
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=1;
        while (n!=0){
            System.out.println("请输入盘子个数：");
            n=sc.nextInt();
            i=0;
            han(n,'a','b','c');
        }

    }
}
