package com.ybliu.cloud.stream1.controller;

import com.ybliu.cloud.stream1.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by linlinyeyu on 2018/11/5
 */
@RestController
public class ProducerController {
    @Autowired
    private SendService sendService;

    @RequestMapping("/send/{msg}")
    public void send(@PathVariable("msg")String msg) {
        sendService.sendMsg(msg);
    }
}
