package com.test.arithmetic.list.sort;

import com.test.arithmetic.list.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 对list中对象排序
 * Created by Administrator on 2016/3/29.
 */
public class SortList {
    List<Student> list;
    @Before
    public void setUp(){
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int v = (int)(Math.random() * 100);
            list.add(new Student(v,"_"+v,18+v));
        }
        System.out.println("原list："+list);
    }
    //方法一，对象实现Comparable接口
    @Test
    public void byImplements(){
//        Collections.sort(list);
        System.out.println("排序后："+list);
    }

    /*方法二，添加比较器*/
    @Test
    public void byOverideCompare(){

        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getId()-o2.getId();
            }
        });
        System.out.println(list);
    }
}
