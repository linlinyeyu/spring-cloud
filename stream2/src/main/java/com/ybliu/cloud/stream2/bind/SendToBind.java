package com.ybliu.cloud.stream2.bind;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by linlinyeyu on 2018/11/7
 */
public interface SendToBind {
    @Output("myInput")
    SubscribableChannel output();

    @Input("input")
    SubscribableChannel input();
}
