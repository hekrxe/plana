package com.hekrxe.plana.minor.design.observer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tanhuayou on 2019/02/25
 */
public abstract class AbstractSubject implements Subject {
    private final Map<String, Observer> observers = new ConcurrentHashMap<>();

    @Override
    public void addObserver(Observer observer) {
        if (null != observer) {
            observers.putIfAbsent(observer.name(), observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (null != observer) {
            observers.remove(observer.name());
        }
    }

    @Override
    public void notifyObserver() {
        observers.forEach((k, observer) -> observer.update());
    }
}
