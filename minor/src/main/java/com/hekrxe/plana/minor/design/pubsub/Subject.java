package com.hekrxe.plana.minor.design.pubsub;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notice();
}
