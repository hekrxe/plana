package com.hekrxe.plana.minor.design.observer;

/**
 * @author tanhuayou on 2019/02/25
 */
public interface Subject {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();

    void operation();
}
