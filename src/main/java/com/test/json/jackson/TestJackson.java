package com.test.json.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by rmiao on 9/6/2016.
 */
public class TestJackson {
    private final static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testReadToList() throws Exception {
        String expected = "[{\"name\":\"Ryan\"},{\"name\":\"Test\"},{\"name\":\"Leslie\"}]";
        List<User> list = Arrays.asList(
                new User("Ryan"),
                new User("Test"),
                new User("Leslie")
        );
        String str = mapper.writeValueAsString(list);
        Assert.assertEquals(expected, str);

        ArrayList arrayList = mapper.readValue(expected, ArrayList.class);
        Object o = arrayList.get(0);
        Assert.assertTrue(o instanceof LinkedHashMap);

        ArrayType arrayType = mapper.getTypeFactory().constructArrayType(User.class);
        User[] users = mapper.readValue(expected, arrayType);
        Assert.assertEquals("Ryan", users[0].getName());

        expected="[{\"a\":12},{\"b\":23}]";
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class);
        //the sieze of the list is dependon the str json length although the json content is not the POJO type maybe
        List<User> userList = mapper.readValue(expected, listType);
        Assert.assertEquals(2, userList.size());
        Assert.assertNull(userList.get(0).getName());

    }

    @Test
    public void testReadAndWritePOJO() throws Exception {
        String expected = "{\"name\":\"Test\"}";
        String test = mapper.writeValueAsString(new User("Test"));
        Assert.assertEquals(expected, test);


        User user = mapper.readValue(expected, User.class);
        Assert.assertEquals("Test", user.getName());

    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class User implements Serializable {
    private static final long serialVersionUID = -5952920972581467417L;
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                '}';
    }
}
