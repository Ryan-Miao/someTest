package com.test.java8.optional;


import com.test.java.lambda.entity.User;
import org.junit.Test;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by Ryan Miao on 1/22/18.
 */
public class OptionalExample {

    @Test
    public void NullTest(){
        final User user = null;
        final String name = Optional.ofNullable(user)
                .map(User::getName)
                .orElse("The user is null.");

        System.out.println(name);
    }

    @Test
    public void NoneNullTest(){
        final User user = new User(1, "Ryan");
        final String name = Optional.ofNullable(user)
                .map(User::getName)
                .orElse("The user is null.");

        System.out.println(name);
    }
}
