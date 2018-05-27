package com.hekrxe.plana.minor.design.lifecycle;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class CommonListener implements LifecycleListener {

    @Override
    public void onEvent(LifecycleEvent event) {
        System.out.println("================================");
        System.out.println("Lifecycle: " + event.getTrigger().getClass().getSimpleName());
        System.out.println("State: " + event.getState());
        System.out.println("Source: " + event.getSource());
    }
}
