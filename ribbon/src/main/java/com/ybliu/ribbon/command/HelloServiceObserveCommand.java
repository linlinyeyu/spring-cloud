package com.ybliu.ribbon.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by linlinyeyu on 2018/10/29
 */
public class HelloServiceObserveCommand extends HystrixObservableCommand<String> {
    private RestTemplate restTemplate;

    public HelloServiceObserveCommand(String commandGroupKey,RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey(commandGroupKey));
        this.restTemplate = restTemplate;
    }

    @Override
    protected Observable<String> construct() {
        //观察者订阅网络请求事件
        return Observable.unsafeCreate(subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    System.out.println("方法执行....");
                    String result = restTemplate.getForEntity("http://config-client/hi",String.class).getBody();
                    //这个方法监听方法，是传递结果的，请求多次的结果通过它返回去汇总起来
                    subscriber.onNext(result);
                    String result1 = restTemplate.getForEntity("http://config-client/hi",String.class).getBody();
                    subscriber.onNext(result1);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    //服务降级Fallback

    @Override
    protected Observable<String> resumeWithFallback() {
        return Observable.unsafeCreate(subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("error");
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}
