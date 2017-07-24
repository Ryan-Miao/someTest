package com.test.java8.c3;

import com.test.java8.c2.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

/**
 * Created by ryan on 7/20/17.
 */
public class AppleSort {
    private List<Apple> inventory;

    @Before
    public void setUp() {
        inventory = new ArrayList<>();
        inventory.add(new Apple("red", 1));
        inventory.add(new Apple("green", 3));
        inventory.add(new Apple("red", 2));
        inventory.add(new Apple("yellow", 21));
    }

    @Test
    public void sort_old() {
        Collections.sort(inventory, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });

        printApples();
    }

    @Test
    public void sort1(){
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });

        printApples();
    }

    @Test
    public void sort2(){
        inventory.sort((o1, o2) -> o1.getWeight() - o2.getWeight());

        printApples();
    }

    @Test
    public void sort3(){
        inventory.sort(Comparator.comparing((Apple a)->a.getWeight()));

        printApples();
    }
    @Test
    public void sort4(){
        inventory.sort(Comparator.comparing(Apple::getWeight));

        printApples();
    }
    @Test
    public void sort5(){
        inventory.sort(comparingInt(Apple::getWeight));

        printApples();
    }

    @Test
    public void sort6(){
        inventory.sort(comparingInt(Apple::getWeight).reversed());

        printApples();
    }

    /**
     * 比较器链
     */
    @Test
    public void sort7(){
       inventory.sort(comparing(Apple::getColor)
               .reversed()
               .thenComparingInt(Apple::getWeight));
    }

    private void printApples() {
        inventory.forEach(System.out::println);
    }
}
