package com.hekrxe.flow;

/**
 * @author tanhuayou on 2019/02/25
 */
@FunctionalInterface
public interface FlowPostProcessor<T> {

    T postProcessBeforeInitialization(T bean);

}
