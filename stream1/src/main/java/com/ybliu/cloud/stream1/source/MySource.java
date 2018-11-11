package com.ybliu.cloud.stream1.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * Created by linlinyeyu on 2018/11/6
 */
@Component
public interface MySource {
    @Output("myOutput")
    MessageChannel myOutput();
}
