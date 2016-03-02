package com.test.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mrf on 2016/3/2.
 */
@Controller
public class TestController {
    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        modelMap.put("msg", 123);

        return "test";
    }
}
