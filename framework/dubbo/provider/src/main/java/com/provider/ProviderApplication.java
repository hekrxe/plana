package com.provider;

import com.provider.test.Action;
import com.provider.test.Service1;
import com.provider.test.Service2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: tanhuayou
 * Date: 2018/5/24
 */

@RestController
@SpringBootApplication
//@EnableDubboProvider
//@DubboComponentScan(basePackageClasses = {ProviderApplication.class})
public class ProviderApplication implements InitializingBean {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private Service1 service1;
    @Autowired
    private Service2 service2;
    @Autowired
    private Action action;

    public ProviderApplication() {
        System.out.println(getClass().getName());
    }

    @RequestMapping(value = "/hi")
    public String hello() {
        ProviderApplication bean = applicationContext.getBean(ProviderApplication.class);
        System.out.println(bean.getClass().getName());
        return action.doAction("hi");
    }

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(getClass().getName());
    }
}
