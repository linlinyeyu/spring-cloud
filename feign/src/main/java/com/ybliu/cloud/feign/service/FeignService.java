package com.ybliu.cloud.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by linlinyeyu on 2018/10/31
 */
@FeignClient(value = "config-client",fallback = FeignFallBack.class)
@Service
public interface FeignService {
    //服务中方法映射路径
    @RequestMapping("/hi")
    String hello();

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello(@RequestParam("name")String name);

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    String hello(@RequestParam("name")String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello(@RequestBody Map<String,Object> user);
}
