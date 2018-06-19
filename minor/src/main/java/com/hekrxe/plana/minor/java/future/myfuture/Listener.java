package com.hekrxe.plana.minor.java.future.myfuture;

/**
 * User: tanhuayou
 * Date: 2018/6/20
 */
public interface Listener<V> {
    /**
     * 当操作完成后回调
     *
     * @param v 最终结果
     */
    void onCompleted(V v);
}
