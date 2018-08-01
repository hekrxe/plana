package com.asb2x.study;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

/**
 * User: tanhuayou
 * Date: 2018/7/28
 */
public class BeanWrapperTest {
    private String name;

    public String getName() {
        return name;
    }

    public BeanWrapperTest setName(String name) {
        this.name = name;
        return this;
    }

    public static void main(String[] args) {
        beanWrapperTest();
    }

    // BeanWrapper 是Spring提供的一个用来操作JavaBean属性的工具
    // 必须要有setter方法
    private static void beanWrapperTest() {
        BeanWrapperTest test = new BeanWrapperTest();


        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(test);
        beanWrapper.setPropertyValue("name", "11");
        System.out.println(test.name);
        beanWrapper.setPropertyValue("name", "22");
        System.out.println(test.name);

    }
}
