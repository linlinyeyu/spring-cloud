package com.ybliu.ribbon.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by linlinyeyu on 2018/10/30
 */
public class HjcCommand extends HystrixCommand<List<String>> {
    private RestTemplate restTemplate;
    private List<Long> ids;

    public HjcCommand(String commandGroupKey,RestTemplate restTemplate,List<Long> ids) {
        //根据commandGroupKey进行线程隔离
        super(HystrixCommandGroupKey.Factory.asKey(commandGroupKey));
        this.restTemplate = restTemplate;
        this.ids = ids;
    }
    @Override
    protected List<String> run() throws Exception {
        System.out.println("发送请求。。。。参数为："+ids.toString()+Thread.currentThread().getName());
        String[] result = restTemplate.getForEntity("http://config-client/hjc?ids={1}",String[].class, StringUtils.join(ids,",")).getBody();
        return Arrays.asList(result);
    }
}
