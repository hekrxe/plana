package com.hekrxe.flow;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tanhuayou on 2019/02/25
 */
@Component
public class TypeBeanFactory implements BeanFactoryAware, BeanFactoryPostProcessor {
    private ConfigurableListableBeanFactory beanFactory;
    private ConcurrentHashMap<String, List<Object>> cached = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> clazz) {
        String key = clazz.getName();
        return (List<T>) cached.computeIfAbsent(key, k -> {
            String[] names = beanFactory.getBeanNamesForType(clazz);
            List<T> objs = new ArrayList<>(names.length);
            for (String name : names) {
                objs.add(beanFactory.getBean(name, clazz));
            }
            OrderComparator.sort(objs);
            return Collections.unmodifiableList(objs);
        });
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> get(Class<T> clazz, boolean priorityOrdered, boolean ordered) {
        String key = clazz.getName() + "_" + priorityOrdered + "_" + ordered;
        return (List<T>) cached.computeIfAbsent(key, k -> {
            String[] names = beanFactory.getBeanNamesForType(clazz);
            List<T> objs = new ArrayList<>(names.length / 3);
            for (String name : names) {
                boolean matchPriority = beanFactory.isTypeMatch(name, PriorityOrdered.class);
                boolean mathOrdered = beanFactory.isTypeMatch(name, Ordered.class);

                if ((priorityOrdered && matchPriority)) {
                    objs.add(beanFactory.getBean(name, clazz));
                } else if (!matchPriority && ordered && mathOrdered) {
                    objs.add(beanFactory.getBean(name, clazz));
                }

                if (!priorityOrdered && !ordered && !matchPriority && !mathOrdered) {
                    objs.add(beanFactory.getBean(name, clazz));
                }
            }
            OrderComparator.sort(objs);
            return Collections.unmodifiableList(objs);
        });
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (null == this.beanFactory && beanFactory instanceof ConfigurableListableBeanFactory) {
            this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}