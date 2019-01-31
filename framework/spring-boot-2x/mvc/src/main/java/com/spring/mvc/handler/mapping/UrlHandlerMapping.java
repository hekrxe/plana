package com.spring.mvc.handler.mapping;

import com.spring.mvc.servlet.ZuulServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

/**
 * @author tanhuayou on 2019/01/30
 */
@Component
public class UrlHandlerMapping extends AbstractUrlHandlerMapping {

    @Autowired
    private ZuulServlet zuulServlet;

    @Override
    protected void initApplicationContext() throws BeansException {
        super.initApplicationContext();
        // 这个路由我(zuulServlet)管了
        registerHandler("/abc/*", zuulServlet);
    }

    @Override
    public int getOrder() {
        return super.getOrder() - 1;
    }
}
