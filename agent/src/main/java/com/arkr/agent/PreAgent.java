package com.arkr.agent;

import java.lang.instrument.Instrumentation;

/**
 * -javaagent:/Users/tanhuayou/code/plana/agent/target/agent.jar
 * User: tanhuayou
 * Date: 2018/5/31
 */
public class PreAgent {
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("PreAgent Started!");
        instrumentation.addTransformer(new PreClassFileTransformer());
    }
}
