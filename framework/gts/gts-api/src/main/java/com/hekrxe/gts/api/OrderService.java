package com.hekrxe.gts.api;

import com.hekrxe.gts.common.model.Order;

/**
 * @author tanhuayou on 2019/02/18
 */
public interface OrderService {

    Order create(String userId, String commodityCode, int orderCount);
}
