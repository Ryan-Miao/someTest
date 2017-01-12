package com.test.java.lambda;

import com.test.java.lambda.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by rmiao on 12/8/2016.
 */
public class TestList {

    @Test
    public void testForeach(){
        List<User> users = Arrays.asList(new User(), new User());
        System.out.println(users);

        users.forEach(user -> user.setId((int)(Math.random()*100)));

        System.out.println(users);
    }

    @Test
    public void testMap(){
        List<User> users = Arrays.asList(
                new User(1,"Ryan"),
                new User(2, "Leslie"),
                new User(3, "Test")
        );
        //collect user ids
        Stream<Integer> integerStream = users.stream().map(User::getId);
        List<Integer> ids = integerStream.collect(Collectors.toList());
        Assert.assertEquals("[1, 2, 3]",ids.toString());

        //collect user names
        List<String> names = users.stream().map(user -> user.getName()).collect(Collectors.toList());
        Assert.assertEquals("[Ryan, Leslie, Test]", names.toString());
    }
}
