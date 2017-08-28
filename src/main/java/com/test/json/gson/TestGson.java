package com.test.json.gson;

import com.google.gson.Gson;
import com.test.java.lambda.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by rmiao on 11/8/2016.
 */
public class TestGson {
    public static void main(String[] args) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(1));
        System.out.println(gson.toJson("abc"));
        System.out.println(gson.toJson(new Long(1)));
        System.out.println(gson.toJson(new Date()));

        int one = gson.fromJson("1", int.class);
        Date sdate = gson.fromJson("Nov 8, 2016 6:28:21 PM", Date.class);

    }

    @Test
    public void print(){
        Gson gson = new Gson();
        User user = new User(12, "test");
        String s = gson.toJson(user);
        System.out.println(s);
        Assert.assertEquals("{\"id\":12,\"name\":\"test\"}", s);
    }
}
