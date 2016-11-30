package com.test.yaml;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * https://bitbucket.org/asomov/snakeyaml/wiki/Documentation
 *
 * Created by rmiao on 11/30/2016.
 */
public class SnakeYAMLTest {

    @Test
    public void testRead(){
        Yaml yaml = new Yaml();
        String name = "config.test.yaml";
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(name);
        Object load = yaml.load(resourceAsStream);
        System.out.println(load);
    }
}
