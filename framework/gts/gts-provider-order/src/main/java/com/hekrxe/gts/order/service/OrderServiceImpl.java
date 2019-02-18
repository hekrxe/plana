package com.hekrxe.gts.order.service;

import com.hekrxe.gts.api.OrderService;
import com.hekrxe.gts.common.model.Order;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author tanhuayou on 2019/02/18
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order create(String userId, String commodityCode, int orderCount) {
        return null;
    }
}
