package com.hekrxe.fsm.config;

import com.hekrxe.fsm.event.Events;
import com.hekrxe.fsm.state.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author tanhuayou on 2019/03/22
 */
@Configuration
@EnableStateMachine
public class FsmConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states.withStates()
                .initial(States.STATE1)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions.withExternal()
//                .source(States.STATE1).target(States.STATE2)
//                .event(Events.EVENT1)
//                .and()
//                .withExternal()
                .source(States.STATE2).target(States.STATE1)
                .event(Events.EVENT2);
    }


    @WithStateMachine
    public static class MyBean {

        @OnTransition(target = "STATE1")
        public void toState1() {
            System.out.println("toS1");
        }

        @OnTransition(target = "STATE2")
        public void toState2() {
            System.out.println("toS2");
        }
    }
}
