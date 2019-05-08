package com.hrx.transaction.dao;

import java.io.Serializable;

/**
 * @author hztanhuayou
 *  2017/12/8
 */
public interface DAO extends Serializable {
    String INSERT = " INSERT ";
    String INTO = " INTO ";
    String VALUES = " VALUES ";
    String SELECT = " SELECT ";
    String DELETE = " DELETE ";
    String FROM = " FROM ";
    String COUNT = " COUNT(*) ";
    String WHERE = " WHERE ";
    String AND = " AND ";
    String LIKE = " LIKE ";
    String GROUP_BY = " GROUP BY ";
    String OFFSET = " OFFSET ";
    String LIMIT = " LIMIT ";
    String LIMIT_ONE = " LIMIT 1 ";
    String UPDATE = " UPDATE ";
    String SET = " SET ";
    String ID = " id, ";
    String DISTINCT = " DISTINCT ";
    String IN = " IN ";
    String ORDER_BY = " ORDER BY ";
    String DESC = " DESC ";
}
