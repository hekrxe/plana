package com.hekrxe.plana.minor.design.pubsub;

import java.util.HashSet;
import java.util.Set;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class TeacherSubject implements Subject {
    private Set<Observer> observers = new HashSet<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notice() {
        observers.forEach(Observer::update);
    }
}
