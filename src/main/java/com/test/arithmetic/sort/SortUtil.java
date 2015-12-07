package com.test.arithmetic.sort;

/**
 * Created by Administrator on 2015/12/7.
 * 算法第四版中排序案例工具
 */
public class SortUtil {

    public static void sort(Comparable[] a){

    }
    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }
    public static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

}
