package com.test.java.tenum;

/**
 * 枚举嵌套
 * Created by Administrator on 2016/3/30.
 */
public enum SecurityCategory {
    STOCK(Security.Stock.class),
    BOND(Security.Bond.class);

    Security[] values;
    SecurityCategory(Class<? extends Security> kind){
        values = kind.getEnumConstants();
    }
    interface Security{
        enum Stock implements Security{SHORT,LONG,MARGIN}
        enum Bond implements Security{MUNICIPAL,JUNK}
    }
    public Security randomSelection(){
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            SecurityCategory random = Enums.random(SecurityCategory.class);
            System.out.println(random+":"+random.randomSelection());
        }
    }
}

/**
 * 同理，food
 */
enum Meal2{
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class);

    private Food[] values;
    private Meal2(Class<? extends Food> kind){
        values = kind.getEnumConstants();
    }
    public interface Food{
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

    public Food randomSelection(){
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (Meal2 meal2 : Meal2.values()) {
                Food food = meal2.randomSelection();
                System.out.println(food);
            }
            System.out.println("----------------------------");
        }
    }

}
