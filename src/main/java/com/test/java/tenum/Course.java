package com.test.java.tenum;

/**
 * 枚举的枚举
 * Created by Administrator on 2016/3/30.
 */
public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class);

    private Food[] values;
    private Course(Class<? extends Food> kind){
        values = kind.getEnumConstants();
    }
    public Food randomSelection(){
        return Enums.random(values);
    }
}

class Meal{
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (Course course : Course.values()) {
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("-------");
        }
    }
}


