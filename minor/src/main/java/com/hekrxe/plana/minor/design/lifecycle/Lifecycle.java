package com.hekrxe.plana.minor.design.lifecycle;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public interface Lifecycle {
    int BEFORE_START = 1 << 1;
    int START_AFTER = 1 << 4;

    int BEFORE_END = 1 << 16;
    int END_AFTER = 1 << 20;

    void start();

    void end();

    void addListener(LifecycleListener lifecycleListener);
}
