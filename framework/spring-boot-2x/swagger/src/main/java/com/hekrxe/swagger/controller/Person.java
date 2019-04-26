package com.hekrxe.swagger.controller;

import java.io.Serializable;

/**
 * @author tanhuayou on 2019/04/13
 */
public class Person implements Serializable {
    private String age;
    private String country;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
