package com.hekrxe.plana.minor.design.lifecycle;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class Engine implements Lifecycle {
    private LifecycleSupport support = new LifecycleSupport(this);

    @Override
    public void start() {
        support.fireEvent(BEFORE_START, "Engine Starting");
        System.out.println("start Engine....");
        support.fireEvent(START_AFTER, "Engine Started");
    }

    @Override
    public void end() {
        support.fireEvent(BEFORE_END, "Engine Ending");
        System.out.println("end Engine....");
        support.fireEvent(END_AFTER, "Engine Ended");
    }

    @Override
    public void addListener(LifecycleListener lifecycleListener) {
        if (null != lifecycleListener) {
            support.addListener(lifecycleListener);
        }
    }
}
