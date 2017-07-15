package com.test.java8.c2;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 2017/07/15/0015.
 */
public class AppleFilter {

    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory){
            if ("green".equals(apple.getColor())){
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color){
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if (apple.getColor()!=null && apple.getColor().equals(color)){
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)){
                result.add(apple);
            }
        }

        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter){
        for (Apple apple : inventory) {
            String format = formatter.format(apple);
            System.out.println(format);
        }
    }

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple("red", 100));
        inventory.add(new Apple("red", 200));
        inventory.add(new Apple("green", 200));

        prettyPrintApple(inventory, new AppleFormatter() {
            @Override
            public String format(Apple apple) {
                String characteristic = apple.getWeight()>150?"heavy":"light";
                return "A " + characteristic + " " + apple.getColor() + " apple.";
            }
        });

        prettyPrintApple(inventory, apple -> "An apple of " + apple.getWeight() + "g");


        List<Apple> redHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
        Assert.assertEquals(1, redHeavyApples.size());
        Assert.assertEquals(200, redHeavyApples.get(0).getWeight());

        List<Apple> apples = filterApples(inventory, apple -> "red".equals(apple.getColor()) && apple.getWeight() > 150);
        Assert.assertEquals(1, apples.size());
        Assert.assertEquals(200, apples.get(0).getWeight());

    }


}
