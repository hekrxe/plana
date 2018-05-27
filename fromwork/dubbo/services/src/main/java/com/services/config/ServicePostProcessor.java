package com.services.config;

import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.beans.factory.annotation.ServiceAnnotationBeanPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author hztanhuayou
 * 2018/2/10
 * @see Service
 * @see Reference
 * @see ServiceAnnotationBeanPostProcessor
 * @see ReferenceBeforeProcessor
 */
class ServicePostProcessor implements BeanPostProcessor {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ServiceConfig) {
            ServiceConfig config = (ServiceConfig) bean;
            Class exportClass = config.getInterfaceClass();
            String exportClassName = exportClass.getName();
            String fetch = DubboVersionSupport.fetch(exportClassName);
            if (null == fetch || fetch.trim().length() == 0) {
                // 没有手动设置版本号 不给启动
                logger.error(exportClassName + " version not found");
                throw new UnsupportedOperationException(exportClassName + " version not found");
            }
            logger.info(exportClassName + ": origin=" + config.getVersion() + ", newest=" + fetch);
            config.setVersion(fetch);
        }
        return bean;
    }
}
