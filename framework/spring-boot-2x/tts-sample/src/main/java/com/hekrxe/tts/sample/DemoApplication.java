package com.hekrxe.tts.sample;

import com.hekrxe.tts.method.annotation.EnableTtsMethod;
import com.hekrxe.tts.sample.method.InterfaceDemo;
import com.hekrxe.tts.sample.method.MethodDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanhuayou on 2019/02/14
 */
@EnableTtsMethod
@RestController
@SpringBootApplication
public class DemoApplication {
    @Autowired
    private InterfaceDemo methodDemo;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/hi")
    public String hi(@RequestParam("name") String name) {
        return methodDemo.hi(name);
    }
}
