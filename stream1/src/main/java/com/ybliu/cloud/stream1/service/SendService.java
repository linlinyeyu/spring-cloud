package com.ybliu.cloud.stream1.service;

import com.ybliu.cloud.stream1.source.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by linlinyeyu on 2018/11/5
 */
@EnableBinding(MySource.class)
public class SendService {
    @Autowired
    private MySource mySource;

    public void sendMsg(String msg) {
        mySource.myOutput().send(MessageBuilder.withPayload(msg).build());
    }
}
