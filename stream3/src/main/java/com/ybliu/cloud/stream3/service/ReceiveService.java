package com.ybliu.cloud.stream3.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Created by linlinyeyu on 2018/11/5
 */
@EnableBinding(Sink.class)
public class ReceiveService {
    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        System.out.println(payload);
    }
}
