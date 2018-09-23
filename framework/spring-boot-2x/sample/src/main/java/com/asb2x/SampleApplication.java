package com.asb2x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;

/**
 * User: tanhuayou
 * Date: 2018/5/23
 */
@Controller
@ImportResource({
        "classpath:config/spring.xml"
})
@SpringBootApplication
public class SampleApplication implements EnvironmentAware {
    private Environment environment;

    @RequestMapping("/")
    @ResponseBody
    String home(HttpServletRequest request) {
        Object attribute = request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);

        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        String resolvePlaceholders = environment.resolvePlaceholders("hi ${customer.key}");
        System.out.println(resolvePlaceholders);
    }
}
