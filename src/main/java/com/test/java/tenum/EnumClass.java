package com.test.java.tenum;

/**
 * 了解enum特性
 * Created by Administrator on 2016/3/30.
 */
public class EnumClass {
    public static void main(String[] args) {
        for (Shrubbery s : Shrubbery.values()) {
            //s继承Enum
            //ordinal表示声明的顺序
            System.out.print(s+" ordinal:"+s.ordinal());
            //enum自动实现了Comparable接口
            System.out.println(" compareTo:"+s.compareTo(Shrubbery.CRAWING));
            //编译器提供了equals和hashCode
            System.out.println(s.equals(Shrubbery.CRAWING));
            System.out.println(s==Shrubbery.CRAWING);
            System.out.println(s.getDeclaringClass());
            System.out.println(s.name());
            System.out.println("======================");
        }
        for (String s :
                "HANGING CRAWING GROUND".split(" ")) {
            Shrubbery shrubbery = Enum.valueOf(Shrubbery.class, s);
            System.out.println(shrubbery);

        }
    }
}

enum Shrubbery{
    GROUND,CRAWING,HANGING
}
