package com.hekrxe.plana.minor.design.lifecycle;

import java.util.EventListener;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public interface LifecycleListener extends EventListener {
    void onEvent(LifecycleEvent event);
}
