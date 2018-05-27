package com.hekrxe.plana.minor.design.lifecycle;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class Context implements Lifecycle {
    private LifecycleSupport support = new LifecycleSupport(this);

    @Override
    public void start() {
        support.fireEvent(BEFORE_START, "Context Starting");
        System.out.println("start Context....");
        support.fireEvent(START_AFTER, "Context Started");
    }

    @Override
    public void end() {
        support.fireEvent(BEFORE_END, "Context Ending");
        System.out.println("end Context....");
        support.fireEvent(END_AFTER, "Context Ended");
    }

    @Override
    public void addListener(LifecycleListener lifecycleListener) {
        if (null != lifecycleListener) {
            support.addListener(lifecycleListener);
        }
    }
}
