package com.ybliu.cloud.feign.controller;

import com.ybliu.cloud.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by linlinyeyu on 2018/11/1
 */
@RestController
public class ConsumerController {
    @Autowired
    private FeignService feignService;

    @RequestMapping("/consumer")
    public String helloConsumer() {
        return feignService.hello();
    }

    @RequestMapping("/consumer2")
    public String helloConsumer2() {
        String r1 = feignService.hello("hjc");
        String r2 = feignService.hello("hjc",23).toString();
        String r3 = feignService.hello(new HashMap<String, Object>(){{put("name","hjc");put("age",23);}});
        return r1+"-------"+r2+"-------"+r3;
    }
}
