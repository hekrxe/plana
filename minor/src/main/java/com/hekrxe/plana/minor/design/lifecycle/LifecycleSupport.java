package com.hekrxe.plana.minor.design.lifecycle;

import java.util.ArrayList;
import java.util.List;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public final class LifecycleSupport {
    private List<LifecycleListener> listeners = new ArrayList<>();

    private Lifecycle realLifecycle;

    public LifecycleSupport(Lifecycle realLifecycle) {
        this.realLifecycle = realLifecycle;
    }

    public void addListener(LifecycleListener lifecycleListener) {
        listeners.add(lifecycleListener);
    }

    public void fireEvent(int state, Object data) {
        listeners.forEach(listener ->
                listener.onEvent(new LifecycleEvent(data).setTrigger(realLifecycle).setState(state))
        );
    }
}
