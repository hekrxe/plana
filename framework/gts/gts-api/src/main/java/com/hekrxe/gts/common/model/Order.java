package com.hekrxe.gts.common.model;

import java.io.Serializable;

/**
 * @author tanhuayou on 2019/02/18
 */
public class Order implements Serializable {
    private long id;
    private String userId;
    private String commodityCode;
    private int count;
    private int money;

    public long getId() {
        return id;
    }

    public Order setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Order setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public Order setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
        return this;
    }

    public int getCount() {
        return count;
    }

    public Order setCount(int count) {
        this.count = count;
        return this;
    }

    public int getMoney() {
        return money;
    }

    public Order setMoney(int money) {
        this.money = money;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", commodityCode='" + commodityCode + '\'' +
                ", count=" + count +
                ", money=" + money +
                '}';
    }
}
