package com.hekrxe.plana.minor.design.lifecycle;

import java.util.EventObject;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class LifecycleEvent extends EventObject {
    /**
     * 本事建的触发者
     */
    private Lifecycle trigger;

    private int state;

    public LifecycleEvent(Object source) {
        super(source);
    }

    public Lifecycle getTrigger() {
        return trigger;
    }

    public LifecycleEvent setTrigger(Lifecycle trigger) {
        this.trigger = trigger;
        return this;
    }

    public int getState() {
        return state;
    }

    public LifecycleEvent setState(int state) {
        this.state = state;
        return this;
    }
}
