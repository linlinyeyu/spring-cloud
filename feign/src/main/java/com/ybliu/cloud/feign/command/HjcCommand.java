package com.ybliu.cloud.feign.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Created by linlinyeyu on 2018/11/2
 */
public class HjcCommand extends HystrixCommand {
    protected HjcCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    @Override
    protected Object run() throws Exception {
        return null;
    }
}
