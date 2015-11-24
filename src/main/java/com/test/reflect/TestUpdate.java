package com.test.reflect;

import java.lang.reflect.Array;

/**
 * Created by Administrator on 2015/11/24.
 * 通过反射修改
 */
public class TestUpdate {
    public static void main(String[] args) {
        int [] temp ={1,2,3,4,5,6,7};
        int [] newTemp = (int[])arrInc(temp,15);
        print(newTemp);
        System.out.println("================");
        String [] atr = {"a","b","v"};
        String [] str=(String[])arrInc(atr,8);
        print(str);
    }

    /**
     * 修改数组的大小
     * @param obj
     * @param len
     * @return
     */
    private static Object arrInc(Object obj, int len) {
        Class<?> arr = obj.getClass().getComponentType();
        Object newArr = Array.newInstance(arr, len);
        int co = Array.getLength(obj);
        System.arraycopy(obj,0,newArr,0,co);
        return newArr;
    }

    /**
     * 打印
     * @param obj
     */
    public static void print(Object obj){
        Class<?> c = obj.getClass();
        if(!c.isArray()){
            return;
        }
        System.out.println("数组长度"+Array.getLength(obj));
        for (int i = 0; i<Array.getLength(obj); i++){
            System.out.println(Array.get(obj,i)+" ");
        }
    }
}

