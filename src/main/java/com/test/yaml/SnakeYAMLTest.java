package com.test.yaml;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

/**
 * https://bitbucket.org/asomov/snakeyaml/wiki/Documentation
 *
 * Created by rmiao on 11/30/2016.
 */
public class SnakeYAMLTest {

    @Test
    public void testRead() throws IOException {
        final DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);

        Yaml yaml = new Yaml(options);
        String name = "parking2.yml";
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(name);
        Object load = yaml.load(resourceAsStream);
        System.out.println(load);

        Writer writer = new FileWriter("D:\\workspace\\test\\someTest\\src\\main\\resources\\parking3.yml");
        yaml.dump(load, writer);
    }
}
