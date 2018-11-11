package com.ybliu.cloud.stream1.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Created by linlinyeyu on 2018/11/7
 */
@EnableBinding({Sink.class})
public class ReceiveService {
    @StreamListener("input")
    public void receiveObject(Object payload) {
        System.out.println(payload);
    }
}
