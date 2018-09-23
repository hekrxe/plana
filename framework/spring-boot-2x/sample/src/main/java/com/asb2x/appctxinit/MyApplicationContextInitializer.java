package com.asb2x.appctxinit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * User: tanhuayou
 * Date: 2018/9/7
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        applicationContext.addBeanFactoryPostProcessor(MyApplicationContextInitializer::postProcessBeanFactory);
        applicationContext.addApplicationListener(MyApplicationContextInitializer::onApplicationEvent);
    }

    private static void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            System.out.println("bbbbbbbb");
        }
    }

    private static void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("aaaaaaaaaaaa");
    }

}
