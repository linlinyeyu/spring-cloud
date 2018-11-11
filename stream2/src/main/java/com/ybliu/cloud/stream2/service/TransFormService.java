package com.ybliu.cloud.stream2.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * Created by linlinyeyu on 2018/11/6
 */
//@EnableBinding(Processor.class)
public class TransFormService {
    //@ServiceActivator(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
    public Object transform(Object payload) {
        System.out.println("消息中转站:"+payload);
        return payload;
    }
}
