package com.hekrxe.flow;

import java.util.EventObject;

/**
 * @author tanhuayou on 2019/02/25
 */
public abstract class AbstractFlowEvent extends EventObject {
    private final long timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public AbstractFlowEvent(Object source) {
        super(source);
        timestamp = System.currentTimeMillis();
    }
}
