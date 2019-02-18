package com.hekrxe.gts.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tanhuayou on 2019/02/15
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.hekrxe.gts.consumer")
public class Consumer {

    public static void main(String[] args) {
        SpringApplication.run(Consumer.class, args);
    }
}
