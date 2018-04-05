package com.hekrxe.mybatis.dao;

import com.hekrxe.mybatis.model.User;

/**
 * User: tanhuayou
 * Date: 2018/4/5
 */
public interface UserDAO {

    int insert(User user);

    User queryById(Long id);
}
