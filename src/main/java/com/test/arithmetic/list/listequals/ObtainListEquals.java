package com.test.arithmetic.list.listequals;
import com.test.arithmetic.list.Student;
import org.junit.Assert;

import java.util.*;

/**
 * 取出list中重复的Student对象
 * Created by Administrator on 2016/3/29.
 */
public class ObtainListEquals {
    public static void main(String[] args){
        //原始数据
        List<Student> list = new ArrayList<>();
        //重复数据
        List<Student> list2 = new ArrayList<>();
        //填充
        for (int i = 0; i < 10 ; i++) {
            list.add(new Student(i,"_"+i,18+i));
            Random random = new Random();
            if (random.nextBoolean()){
                list.add(new Student(i,"_"+i,18+i));
            }
        }
        //使用hashset去重复，set为重复的集合，可以通过new ArrayList(set)转换成list
        HashSet<Student> set = new HashSet<>();
        for (Student student : list) {
            boolean add = set.add(student);
            if (!add){
                list2.add(student);
            }
        }
        //比较
        Assert.assertEquals(list.size(),list2.size()+set.size());

    }

}
