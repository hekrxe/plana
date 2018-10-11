package com.inject.component;

import com.inject.demo.DemoComponent;
import com.inject.support.MyAnnotation;
import org.springframework.stereotype.Component;

/**
 * User: tanhuayou
 * Date: 2018/10/11
 */
@Component
public class DemoService {

    @MyAnnotation
    private DemoComponent component;

    public String hi(String msg) {
        return component.hi(msg);
    }

}
