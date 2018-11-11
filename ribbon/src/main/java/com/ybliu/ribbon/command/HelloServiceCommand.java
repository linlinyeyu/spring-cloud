package com.ybliu.ribbon.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

/**
 * Created by linlinyeyu on 2018/10/29
 */
public class HelloServiceCommand extends HystrixCommand<String> {
    private RestTemplate restTemplate;

    public HelloServiceCommand(String commandGroupKey,RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey(commandGroupKey));
        this.restTemplate = restTemplate;
    }

    //服务调用
    @Override
    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return restTemplate.getForEntity("http://config-client/hi",String.class).getBody();
    }

    @Override
    protected String getFallback() {
        return "error";
    }

    //Hystrix的缓存
    @Override
    protected String getCacheKey() {
        return "hello";
    }
}
