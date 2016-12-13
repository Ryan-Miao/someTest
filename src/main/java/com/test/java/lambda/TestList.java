package com.test.java.lambda;

import com.test.java.lambda.entity.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rmiao on 12/8/2016.
 */
public class TestList {

    @Test
    public void testForeach(){
        List<User> users = Arrays.asList(new User(), new User());
        System.out.println(users);

        users.forEach(user -> user.setId("1"));

        System.out.println(users);
    }
}
