package com.ybliu.cloud.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by linlinyeyu on 2018/10/18
 */
@RestController
@RequestMapping("/")
@RefreshScope
public class ConfigController {
    @Value("${myww}")
    private String myww;

    @RequestMapping("/hi")
    public String hi() {
        return myww;
    }

    @RequestMapping("/hello")
    public String helloa() {
        return "hello1";
    }

    @RequestMapping("/hjc")
    public List<String> requestjoin(String ids) {
        List<String> list = new ArrayList<>();
        list.add("h1");
        list.add("h2");
        list.add("h3");
        return list;
    }

    @RequestMapping(value = "hello1",method = RequestMethod.GET)
    public String hello(@RequestParam String name) {
        return "Hello"+name;
    }

    @RequestMapping(value = "hello2",method = RequestMethod.GET)
    public String hello(@RequestParam String name, @RequestHeader Integer age) {
        return "Hello"+name+",age"+age;
    }

    @RequestMapping(value = "hello3",method = RequestMethod.POST)
    public String hello(@RequestBody Map<String,Object> user) {
        return "Hello"+user.get("name")+",age"+user.get("age");
    }
}
