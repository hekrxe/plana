package com.inject;

import com.inject.component.DemoService;
import com.inject.support.BeanProcessorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * User: tanhuayou
 * Date: 2018/10/11
 */

@Import(value = {BeanProcessorRegistrar.class})
@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Override
    public void run(String... args) {
        System.out.println(demoService.hi("msg"));
    }
}
