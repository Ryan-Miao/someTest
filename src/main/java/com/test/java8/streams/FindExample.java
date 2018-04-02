package com.test.java8.streams;

import com.google.common.collect.Lists;
import java.util.List;
import org.junit.Test;

/**
 * Created by Ryan Miao on 4/2/18.
 */
public class FindExample {


    @Test
    public void anyMatchTest() {
        final List<Entity> entities = Lists.newArrayList(new Entity(101),
                new Entity(12), new Entity(33), new Entity(42));

        boolean b = entities.stream().anyMatch(e -> {
            System.out.println(e.getId());
            return e.getId() % 2 == 0;
        });

        if (b) {
            System.out.println("有偶数");
        }

    }

}
