package com.hekrxe.fsm;

import com.hekrxe.fsm.event.Events;
import com.hekrxe.fsm.state.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumSet;

/**
 * @author tanhuayou on 2019/03/22
 */
@RestController
@SpringBootApplication
public class FsmDemo implements CommandLineRunner {
    @Autowired
    private StateMachine<States, Events> stateMachine;


    @GetMapping("/test")
    public Object test() {
        return "OK";
    }

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(FsmDemo.class, args);


        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();

        builder.configureStates()
                .withStates()
                .initial(States.STATE1)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.STATE1).target(States.STATE2)
                .event(Events.EVENT1)
                .and()
                .withExternal()
                .source(States.STATE2).target(States.STATE1)
                .event(Events.EVENT2);

        StateMachine<States, Events> stateMachine = builder.build();
        stateMachine.start();
        stateMachine.sendEvent(Events.EVENT1);
        stateMachine.sendEvent(Events.EVENT2);
    }


    @Override
    public void run(String... args) {
        stateMachine.start();
        stateMachine.sendEvent(Events.EVENT1);
        stateMachine.sendEvent(Events.EVENT2);
    }
}
