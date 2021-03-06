package com.test.json.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

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

        expected="[{\"a\":12},{\"b\":23},{\"name\":\"Ryan\"}]";
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class);
        //the sieze of the list is dependon the str json length although the json content is not the POJO type maybe
        List<User> userList = mapper.readValue(expected, listType);
        Assert.assertEquals(3, userList.size());
        Assert.assertNull(userList.get(0).getName());
        Assert.assertEquals("Ryan",userList.get(2).getName());

    }

    @Test
    public void testReadAndWritePOJO() throws Exception {
        String expected = "{\"name\":\"Test\"}";
        String test = mapper.writeValueAsString(new User("Test"));
        Assert.assertEquals(expected, test);


        User user = mapper.readValue(expected, User.class);
        Assert.assertEquals("Test", user.getName());

    }

    @Test
    public void testDateFormat() throws Exception{
        User user  = new User("Ryan", new Date());
        String s = mapper.writeValueAsString(user);
        System.out.println(s);

        Gson gson = new Gson();
        String s1 = gson.toJson(user);
        System.out.println(s1);

    }

    @Test
    public void testMap() throws IOException {
        String content = "{\n" +
                "  \"booking_date\": \"2016-07-28\",\n" +
                "  \"rooms\": [\n" +
                "    {\n" +
                "      \"nights\": [\n" +
                "        {\n" +
                "          \"night\": \"1\",\n" +
                "          \"cost\": \"100\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Map map = mapper.readValue(content, Map.class);
        System.out.println(map);
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class User implements Serializable {
    private static final long serialVersionUID = -5952920972581467417L;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ",timezone = "GMT")
    private Date birth;

    public User() {
    }

    public User(String name, Date birth) {
        this.name = name;
        this.birth = birth;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
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
