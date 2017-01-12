package com.test.json.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://github.com/alibaba/fastjson/wiki/Compare_JSON_API
 * Created by rmiao on 12/28/2016.
 */
public class TestFastJson {
    @Test
    public void demo(){
        List<String> strings = Arrays.asList("a", "b", "c", "d");
        String s = JSON.toJSONString(strings);
        System.out.println(s);

        Type type = new TypeReference<List<String>>() {}.getType();
        ArrayList arrayList = JSON.parseObject(s, type);
        System.out.println(arrayList);
    }
}
