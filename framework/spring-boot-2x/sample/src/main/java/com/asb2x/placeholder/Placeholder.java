package com.asb2x.placeholder;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * User: tanhuayou
 * Date: 2018/9/7
 */
public class Placeholder implements InitializingBean {
    private String abc;

    @Value("${phValue}")
    private String phValue;


    public Placeholder() {
    }

    public String getAbc() {
        return abc;
    }

    public Placeholder setAbc(String abc) {
        this.abc = abc;
        return this;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("abc=" + abc + ", phValue=" + phValue);
    }
}
