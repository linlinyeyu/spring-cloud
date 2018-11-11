package com.ybliu.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by linlinyeyu on 2018/10/31
 */
@Service
public class HjcService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCollapser(batchMethod = "getbatch",collapserProperties = {@HystrixProperty(name = "timeDelayInMilliseconds",value = "200")})
    public Future<String> batchGetHjc(long id) {
        return null;
    }

    @HystrixCommand
    public List<String> getbatch(List<Long> ids) {
        System.out.println("发送参数。。。参数为:"+ids.toString()+Thread.currentThread().getName());
        String[] result = restTemplate.getForEntity("http://config-client/hjc?ids={1}",String[].class, StringUtils.join(ids,",")).getBody();
        return Arrays.asList(result);
    }
}
