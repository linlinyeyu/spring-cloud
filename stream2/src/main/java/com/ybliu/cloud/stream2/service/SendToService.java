package com.ybliu.cloud.stream2.service;

import com.ybliu.cloud.stream2.bind.SendToBind;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * Created by linlinyeyu on 2018/11/7
 */
@EnableBinding(SendToBind.class)
public class SendToService {
    @StreamListener("input")
    @SendTo("myInput")
    public Object receiveFromInput(Object payload) {
        System.out.println("中转消息"+payload);
        return "xxxxx";
    }
}
