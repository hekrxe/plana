package com.hekrxe.tts.sample.method;

import com.hekrxe.tts.method.annotation.TtsMethod;
import org.springframework.stereotype.Component;

/**
 * @author tanhuayou on 2019/02/14
 */
@Component
public class MethodDemo {

    @TtsMethod
    public String hi(String name) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hi! " + name;
    }
}
