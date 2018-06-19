package com.hekrxe.plana.minor.java.future.myfuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: tanhuayou
 * Date: 2018/6/20
 */
public abstract class FutureTask<V> implements Runnable {
    private V outcome;

    private DefaultListeners<V> listeners = new DefaultListeners<>();

    /**
     * main logic
     *
     * @return v
     * @throws Exception e
     */
    public abstract V call() throws Exception;

    @Override
    public void run() {
        V result;
        boolean fail = false;
        try {
            result = call();
        } catch (Throwable ex) {
            result = null;
            fail = true;
        }
        if (!fail) {
            outcome = result;
            listeners.fire(result);
        }
    }

    @SuppressWarnings("unchecked")
    public FutureTask<V> addListener(Listener<V> listener) {
        if (null != outcome) {
            listener.onCompleted(outcome);
            return this;
        }
        synchronized (this) {
            listeners.add(listener);
            return this;
        }
    }

    private static class DefaultListeners<V> {
        private List<Listener<V>> listeners = new ArrayList<>();

        @SafeVarargs
        final void add(Listener<V>... listener) {
            listeners.addAll(Arrays.asList(listener));
        }

        void fire(V v) {
            listeners.forEach(l -> l.onCompleted(v));
        }

    }
}
