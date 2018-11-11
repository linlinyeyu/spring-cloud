package com.ybliu.cloud.feign.service;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by linlinyeyu on 2018/10/31
 */
@Component
public class FeignFallBack implements FeignService{
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello(String name) {
        return "error";
    }

    @Override
    public String hello(String name, Integer age) {
        return "error";
    }

    @Override
    public String hello(Map<String, Object> user) {
        return "error";
    }
}
