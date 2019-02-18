package com.hekrxe.gts.common.dao;

import com.hekrxe.gts.common.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author tanhuayou on 2019/02/18
 */
public interface OrderDAO {

    @Insert("INSERT INTO order_tbl (user_id, commodity_code,count,money) VALUES(#{userId},#{commodityCode},#{count},#{money})")
    int insert(Order order);

    @Select("SELECT * FROM order_tbl")
    List<Order> list();
}
