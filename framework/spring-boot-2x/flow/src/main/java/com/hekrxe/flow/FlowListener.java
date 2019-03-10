package com.hekrxe.flow;

import java.util.EventListener;

/**
 * @author tanhuayou on 2019/02/25
 */
@FunctionalInterface
public interface FlowListener<E extends AbstractFlowEvent> extends EventListener {

    void onFlowEvent(E event);
}
