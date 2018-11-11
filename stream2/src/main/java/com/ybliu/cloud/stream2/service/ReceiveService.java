package com.ybliu.cloud.stream2.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Created by linlinyeyu on 2018/11/5
 */
//@EnableBinding(Processor.class)
public class ReceiveService {
    //@StreamListener(Processor.INPUT)
    public void receive(Object payload) {
        System.out.println(payload);
    }
}
