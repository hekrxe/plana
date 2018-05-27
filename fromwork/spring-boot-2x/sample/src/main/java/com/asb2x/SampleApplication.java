package com.asb2x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: tanhuayou
 * Date: 2018/5/23
 */
@Controller
@SpringBootApplication
public class SampleApplication implements EnvironmentAware {
    private Environment environment;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }


    public void setEnvironment(Environment environment) {
        this.environment = environment;
        String resolvePlaceholders = environment.resolvePlaceholders("hi ${customer.key}");
        System.out.println(resolvePlaceholders);
    }
}
