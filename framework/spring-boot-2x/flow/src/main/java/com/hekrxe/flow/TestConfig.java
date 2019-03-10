package com.hekrxe.flow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author tanhuayou on 2019/02/25
 */
@Configuration
public class TestConfig {


    @Bean
    public FlowListener flowListener1() {
        return new FlowListener<AbstractFlowEvent>() {
            @Override
            public void onFlowEvent(AbstractFlowEvent event) {
                System.out.println("flowListener1");
            }
        };
    }

    @Bean
    public FlowListener flowListener2() {
        return new FlowListener<AbstractFlowEvent>() {
            @Override
            public void onFlowEvent(AbstractFlowEvent event) {
                System.out.println("flowListener2");
            }
        };
    }


    @Bean
    @Order(value = -1)
    public FlowListener flowListenerOrder1() {
        return new FlowListener<AbstractFlowEvent>() {
            @Override
            public void onFlowEvent(AbstractFlowEvent event) {
                System.out.println("flowListenerOrder1");
            }
        };
    }


    @Bean
    @Order(value = -2)
    public FlowListener flowListenerOrder2() {
        return new FlowListener<AbstractFlowEvent>() {
            @Override
            public void onFlowEvent(AbstractFlowEvent event) {
                System.out.println("flowListenerOrder2");
            }
        };
    }

    @Bean
    @Order(value = -3)
    public FlowListener flowListenerOrder3() {
        return new FlowListener<AbstractFlowEvent>() {
            @Override
            public void onFlowEvent(AbstractFlowEvent event) {
                System.out.println("flowListenerOrder3");
            }
        };
    }


    @Component
    public static class FL implements FlowListener {
        @Override
        public void onFlowEvent(AbstractFlowEvent event) {
            System.out.println("FL");
        }
    }

    @Component
    public static class FL1 implements FlowListener {
        @Override
        public void onFlowEvent(AbstractFlowEvent event) {
            System.out.println("FL1");
        }
    }

    @Component
    public static class FLOrdered1 implements FlowListener, Ordered {
        @Override
        public void onFlowEvent(AbstractFlowEvent event) {
            System.out.println("FLOrdered1");
        }


        @Override
        public int getOrder() {
            return -1;
        }
    }

    @Component
    public static class FLOrdered2 implements FlowListener, Ordered {
        @Override
        public void onFlowEvent(AbstractFlowEvent event) {
            System.out.println("FLOrdered2");
        }

        @Override
        public int getOrder() {
            return -2;
        }
    }


    @Component
    public static class FLPriorityOrdered1 implements FlowListener, PriorityOrdered {
        @Override
        public void onFlowEvent(AbstractFlowEvent event) {
            System.out.println("FLPriorityOrdered1");
        }

        @Override
        public int getOrder() {
            return -1;
        }
    }


    @Component
    public static class FLPriorityOrdered2 implements FlowListener, PriorityOrdered {
        @Override
        public void onFlowEvent(AbstractFlowEvent event) {
            System.out.println("FLPriorityOrdered2");
        }

        @Override
        public int getOrder() {
            return -2;
        }
    }


}
