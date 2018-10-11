package com.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.services.HelloService;
import com.services.config.EnableDubboConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: tanhuayou
 * Date: 2018/5/24
 */

@RestController
@SpringBootApplication
@EnableDubboConsumer
@DubboComponentScan(basePackageClasses = {ConsumerApplication.class})
public class ConsumerApplication {

    @Reference
    public HelloService demoService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return demoService.hello(name);
    }


    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }
}
