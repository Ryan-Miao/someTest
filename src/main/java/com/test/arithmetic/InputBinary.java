package com.test.arithmetic;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InputBinary {

    /**
     * @Title: main
     * @Description: 二分查找
     * <P>二、二分查找
     * 题目：分别有M个和N个顺序排列的数列，人工输入这两个数列，两组数列由低到高排列，输出第（M+N）/2个数的值。其中两组数列不能合并，只能逐个数字进行比较后输出。</P>
     * <p>
     * 题目含义没看懂！ 要求：手动输入两组序列，从小到大，输出中间点
     * 题意不清的地方：手动输入的序列是否是有序的，还是无序再排列。
     * 从小到大中间点的意思是什么，是两组合并后的的顺序排列中间点，还是两组各自排序后选取中间的数
     * 另外，没看到要查找的东西，名字却叫做二分查找，晕
     * </p>
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一组序列，用一个空格或enter键间隔，数字必须是数字。输入0结束");
        int i = sc.nextInt();
        List<Integer> m = new ArrayList<Integer>();
        List<Integer> n = new ArrayList<Integer>();
        while (i != 0) {
            m.add(i);
            i = sc.nextInt();
        }
        System.out.println();
        System.out.println("请输入第二组序列，用一个空格或enter键间隔，数字必须是数字。输入0结束");
        i = sc.nextInt();
        while (i != 0) {
            n.add(i);
            i = sc.nextInt();
        }
        sc.close();
        System.out.println("您输入的数列分别为：");
        System.out.println(m);
        System.out.println(n);
        // 排序
        Collections.sort(m);
        Collections.sort(n);

        // 直接取出两个数列中，位于中间的数
        One(m, n);

        // 两个数列合并排序后，位于中间的数
        Two(m, n);

        //两个数列不合并，进行插入排序，直到中位数。时间复杂度O(n)
        Three(m,n);

    }

    /**
     * @param @param m 设定文件
     * @return void 返回类型
     * @throws
     * @Title: One
     * @Description: 直接取出两个数列中，位于中间的数
     */
    public static void One(List<Integer> m, List<Integer> n) {
        int ms = m.size();
        int ns = n.size();
        int middle;
        if (ms > 0) {
            middle = (ms + 1) / 2;
            System.out.println("第一组数列中间的值：" + m.get(middle - 1));
        }
        if (ns > 0) {
            middle = (ns + 1) / 2;
            System.out.println("第二组数列中间的值：" + n.get(middle - 1));
        }
        if (ms + ns > 0) {
            middle = (ms + ns + 1) / 2;
            if (ms >= middle) {
                System.out.println("两组数列中间的值位于第一列：" + m.get(middle - 1));
            }
            if (ms < middle) {
                System.out.println("两组数列中间的值位于第二列：" + n.get(middle - ms - 1));
            }
        }
    }

    /**
     * @param @param m
     * @param @param n 设定文件
     * @return void 返回类型
     * @throws
     * @Title: Two
     * @Description: 两组数合并排序后，取出位于中间的数
     */
    public static void Two(List<Integer> m, List<Integer> n) {
        List<Integer> list = new ArrayList<>();
        list.addAll(m);
        list.addAll(n);
        Collections.sort(list);

        System.out.println("两组数列合并排序后数列为：" + list);
        System.out.println("两组数列合并排序后中间的值：" + list.get(list.size() / 2-1));
    }

    /**
     * @param @param m
     * @param @param n 设定文件
     * @return void 返回类型
     * @throws
     * @Title: Three
     * @Description: 不合并，每个数字进行比较，取出中间数
     */
    public static void Three(List<Integer> m, List<Integer> n) {
        int ms = m.size();
        int ns = n.size();
        int middle = (ms + ns+1)/2;
        int count = 0;
        double result = 0;

        int i = 0;//m指针
        int j = 0;//n指针
        while (i < ms || j < ns) {
            if(count==middle){//已比较到中位数
                break;
            }
            if(i==ms){//m列扫描结束
                result = n.get(j);
                count++;
                continue;
            }
            if(j==ns){
                result = m.get(i);
                count++;
                continue;
            }
            Integer mt = m.get(i);
            Integer nt = n.get(j);
            if (mt > nt) {
                result = nt;//中位数
                count++;//插入数量
                j++;//扫描较小数的下一个
            }else if(mt==nt){
                result=nt;
                count+=2;
                i++;
                j++;
            }else{
                result = mt;
                count++;
                i++;
            }
        }

        System.out.println("两组数列不合并，插入排序后中间的值："+result);

    }



}
