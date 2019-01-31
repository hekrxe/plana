package com.spring.mvc.handler.adapter;

import com.spring.mvc.servlet.ZuulServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;

/**
 * @author tanhuayou on 2019/01/30
 */
@Configuration
public class ServletHandlerAdapterConfig {

    @Bean
    public SimpleServletHandlerAdapter simpleServletHandlerAdapter() {
        // 支持servlet
        return new SimpleServletHandlerAdapter();
    }

    @Bean
    public ServletRegistrationBean zuulServletRegistration(ZuulServlet zuulServlet) {
        // 这个其实完全可以不要
        return new ServletRegistrationBean<>(zuulServlet, "/zuul/*");
    }
}
