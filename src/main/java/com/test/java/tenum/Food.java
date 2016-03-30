package com.test.java.tenum;

/**
 * 使用接口组织枚举
 * Created by Administrator on 2016/3/30.
 */
public interface Food {
    //开胃食物
    enum Appetizer implements Food{
        SALAD,SOUP,SPRING_ROLLS;
    }
    //主菜
    enum MainCourse implements Food{
        LASNGE,BURRITO,PAD_THAI,LENTILS,HUMMOUS,VINDALOO;
    }
    //甜点
    enum Dessert implements Food{
        TIRAMISU,GELATO,BLACK_FOREST_CAKE,FRUIT,CREME_CARMEL;
    }
    //coffe
    enum Coffee implements Food{
        BLACK_COFFEE,DECAF_COFFEE,ESPRESSO,LATTE,CAPPUCCINO,TEA,HERB_TEA;
    }
    //....
}
class TypeOfFood{
    public static void main(String[] args) {
        Food food = Food.Appetizer.SALAD;
        food = Food.MainCourse.PAD_THAI;
    }

}
