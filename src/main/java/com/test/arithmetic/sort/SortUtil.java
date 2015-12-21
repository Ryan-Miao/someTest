package com.test.arithmetic.sort;

/**
 * Created by Administrator on 2015/12/7.
 * 算法第四版中排序案例工具
 */
public class SortUtil {

    public static void sort(Comparable[] a){

    }

    //选择排序
    private void chooseSort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if(less(a[j],a[min])){
                    min = j;
                }
            }
            exch(a,i,min);
        }
    }

    //插入排序
    private void insertSort(Comparable[] a ){
        int N = a.length;
        for (int i = 1; i < N; i++) {
            //将a[i]插入到a[i-1]、a[i-2]、a[i-3]..之中
            for (int j = i; j >0 && less(a[j],a[j-1]) ; j--) {
                exch(a,j,j-1);
            }
        }
    }

    //插入排序修改,大的向右移动
    private void insertMoveSort(Comparable[] a ){
        int N = a.length;
        for (int i = 1; i < N; i++) {
            Comparable temp = a[i];
            int flag = i;
            for (int j = i-1; j >=0 ; j--) {
                if(less(temp,a[j])){//if ai < aj
                    //大的j右移动
                    a[flag] = a[j];
                    flag=j;//标记ai所处于的位置---也可折半查找位置插入
                }
            }
            a[flag]=temp;
            show(a);
        }
    }

    //希尔排序
    private void shellSort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h<N/3)
            h = 3*h+1;//1 4 13 40 121 364 1093
        while(h>=1){
            for (int i = h; i < N; i++) {
                //将a[i]插入到a[i-h] a[i-2*h] a[i-3*h]..
                for (int j = i; j>=h && less(a[j],a[j-h]); j-=h) {
                    exch(a,j,j-h);
                    show(a);
                }
            }
            h=h/3;
        }
    }

    /**
     * 返回v是否比w小
     * v<w==true
     * @param v
     * @param w
     * @return
     */
    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }
    public static void exch(Comparable[] a,int i,int j){
            Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+",");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++) {
            if(less(a[i],a[i-1])){
                return false;
            }
        }
        //从小到大
        return true;
    }

    public static void main(String[] args){
        SortUtil u = new SortUtil();
//        Integer a[] = {3,2,1,2,46,7,8,2,1};
        String[] a = {"a","b","w","a","y","q","r","b","l","h","g","s","p","o","i","y","t","r","e","w","q"};
        show(a);
        System.out.println(isSorted(a));
//        u.chooseSort(a);
//        u.insertSort(a);
//        u.insertMoveSort(a);
        u.shellSort(a);
        show(a);
    }

}
