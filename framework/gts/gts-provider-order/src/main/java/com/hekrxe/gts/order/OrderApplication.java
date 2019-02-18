package com.hekrxe.gts.order;

import com.hekrxe.gts.common.config.EnableDataSource;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tanhuayou on 2019/02/15
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.hekrxe.gts.order.service")
@EnableDataSource
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
