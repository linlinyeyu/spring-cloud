package com.ybliu.ribbon.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.ybliu.ribbon.command.HelloServiceCommand;
import com.ybliu.ribbon.command.HelloServiceObserveCommand;
import com.ybliu.ribbon.command.HjcBatchCommand;
import com.ybliu.ribbon.command.HjcCommand;
import com.ybliu.ribbon.service.HelloService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by linlinyeyu on 2018/10/25
 */
@RestController
public class ConsumerController {
    //注入负载均衡客户端
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HelloService helloService;

    @RequestMapping("/consumer")
    public String helloConsumer() {
        HystrixRequestContext.initializeContext();
        HystrixCommand command = new HelloServiceCommand("hello",restTemplate);
        String execute = ((HelloServiceCommand) command).execute();
        /*//根据配置文件的providers获取服务
        ServiceInstance serviceInstance = loadBalancerClient.choose("providers");
        //负载均衡默认轮询，轮询取得服务
        URI uri = URI.create(String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()));
        return uri.toString();*/
        return null;
    }

    @RequestMapping("/consumer-service")
    public String consumer() throws ExecutionException, InterruptedException {
        HelloServiceCommand command = new HelloServiceCommand("hello",restTemplate);
        Future<String> result = command.queue();
        return result.get();
    }

    @RequestMapping("/consumer-observable")
    public String consumerObservable() {
        List<String> list = new ArrayList<>();
        HelloServiceObserveCommand command = new HelloServiceObserveCommand("hello",restTemplate);
        //热执行
        Observable<String> observable = command.observe();
        //冷执行
//        Observable<String> observable = command.toObservable();
        //订阅
        observable.subscribe(s -> {
            System.out.println("结果来了......");
            list.add(s);
        },e -> {
            e.printStackTrace();
        },() -> {
            System.out.println("会聚完了所有查询请求");
        });

        return list.toString();
    }

    @RequestMapping("/cosumer-join")
    public String helloConsumerJoin() throws ExecutionException, InterruptedException {
        //请求合并
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        HjcBatchCommand command = new HjcBatchCommand(restTemplate,1L);
        HjcBatchCommand command1 = new HjcBatchCommand(restTemplate,2L);
        HjcBatchCommand command2 = new HjcBatchCommand(restTemplate,3L);
        //为异步，合并请求
        Future<String> future = command.queue();
        Future<String> future1 = command1.queue();
        String r = future.get();
        String r1 = future1.get();
        Thread.sleep(2000);
        //前面两条命令合并，最后一条单独，睡眠时间2000毫秒，设置请求合并时间200毫秒
        Future<String> future2 = command2.queue();
        String r2 = future2.get();
        System.out.println(r);
        System.out.println(r1);
        System.out.println(r2);
        context.close();
        return null;
    }
}
