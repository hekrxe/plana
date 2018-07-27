package com.es.spring.dao.model;

import java.io.Serializable;

/**
 * User: tanhuayou
 * Date: 2018/7/20
 */
public class Person implements Serializable {
    private Integer height;

    public Integer getHeight() {
        return height;
    }

    public Person setHeight(Integer height) {
        this.height = height;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "height=" + height +
                '}';
    }
}
