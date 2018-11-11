package com.ybliu.cloud.feign.command;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.ybliu.cloud.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by linlinyeyu on 2018/11/2
 */
@Service
public class HjcAnnotationCommand {
    @Autowired
    private FeignService feignService;

    //同步方式
    @HystrixCommand
    public Future<String> getEmployeeAsync() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return feignService.hello("hjc");
            }
        };
    }

    @HystrixCommand
    public String getEmployeeAsync1() {
        return feignService.hello("hjc");
    }
}
