package com.test.java.string;

import com.test.java.lambda.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ryan Miao on 9/20/17.
 */
public class TestString {

    /**
     * @author Ryan Miao
     * 等号赋值，注意字面量的存在
     */
    @Test
    public void testNewStr() throws InterruptedException {
        //str.intern(): 若常量池存在，返回常量池中的对象；若常量池不存在，放入常量池，并返回this。
        //=号赋值，若常量池存在，直接返回常量池中的对象0xs1,如果常量池不存在，则放入常量池，常量池中的对象也是0xs1
        String s1 = "RyanMiao";//0xs1
        assertTrue(s1.intern() == s1);//0xs1 == 0xs1  > true
    }

    /**
     * @author Ryan Miao
     *
     * 暴露的字面量(literal)也会生成对象，放入Metaspace
     */
    @Test
    public void testNew() throws InterruptedException {
        //new赋值，直接堆中创建0xs2, 常量池中All literal strings and string-valued constant expressions are interned，
        // "ab"本身就是一个字符串，并放入常量池，故intern()返回0xab
        String s2 = new String("RyanMiao");
        assertFalse(s2.intern() == s2);//0xab == 0xs2  > false

    }

    /**
     * @author Ryan Miao
     * 上栗中，由于字面量(literal)会生成对象，并放入常量池，因此可以直接从常量池中取出(前提是此行代码运行之前没有其他代码运行，常量池是干净的)
     *
     * 本次，测试非暴露字面量的str
     */
    @Test
    public void testConcat() throws InterruptedException {
        //没有任何字面量为"ab"暴露给编译器，所以常量池没有创建"ab"，所以，intern返回this
        String s3 = new StringBuilder("Ryan").append("Miao").toString();
        assertTrue(s3.intern() == s3);

    }

    /**
     * @author Ryan Miao
     * 上栗中，只要不暴露我们最终的字符串，常量池基本不会存在，则每次新建(new)的时候，都会放入常量池，intern并返回本身。即常量池的对象即新建的对象本身。
     *
     * 本次，测试某些常量池已存在的字符串
     */
    @Test
    public void testExist() throws InterruptedException {
        //为毛常量池存在java这个单词
        //s4 == 0xs4, intern发现常量池存在，返回0xexistjava
        String s4 = new StringBuilder("ja").append("va").toString();
        assertFalse(s4.intern() == s4);  //0xexistjava == 0xs4  > false

        //int也一开始就存在于常量池中了， intern返回0xexistint
        String s5 = new StringBuilder().append("in").append("t").toString();
        assertFalse(s5.intern()==s5); // 0xexistint == 0xs5  > false


        //由于字面量"abc"加载时，已放入常量池，故s6 intern返回0xexistabc, 而s6是新建的0xs6
        String a = "abc";
        String s6 = new StringBuilder().append("ab").append("c").toString();
        assertFalse(s6.intern() == s6);  //0xexistabc  == 0xs6 > false

        Thread.sleep(1000*60*60);

    }

    @Test
    public void testHash(){
        String key = "Ryan";

        Map<Object, Object> map = new HashMap<>();
        map.put(key, "1");

        key = "miao";

        Object o = map.get(key);
        Assert.assertNull(o);

        User user = new User(1,"Ryan");
        System.out.println(user.hashCode());
        map.put(user, "Miao");
        user.setName("test");
        System.out.println(user.hashCode());

        Object o1 = map.get(user);
        Assert.assertNull(o1);
    }

    /**
     * 并不会因为顺序而导致结果不同，因为在class load的时候，字面量已经加载到字符串常量池了。
     */
    @Test
    public void testOrder(){
        String a = new String("a");
        a.intern();
        String b = "a";
        String c = "a";

        assertFalse(a == b);
       assertTrue(b == c);
    }
}
