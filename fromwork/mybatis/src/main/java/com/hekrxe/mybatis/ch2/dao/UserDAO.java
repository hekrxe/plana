package com.hekrxe.mybatis.ch2.dao;

import com.hekrxe.mybatis.ch2.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 不需要针对这个接口写具体的实现类,其具体点的实现类由mybatis动态构建
 *
 * @author hztanhuayou
 * @date 2017/12/4
 */
public interface UserDAO {

    @Insert("insert into user(`name`,`age`) values(#{name},#{age})")
    int insert(User user);

    @Delete("delete from user where id=#{id}")
    int delete(@Param("id") Long id);

    @Update("update user set name=#{name},age=#{age} where id=#{id}")
    int update(User user);

    @Select("select * from user where id=#{id}")
    User query(@Param("id") Long id);

    @Select("select * from user")
    List<User> queryAll();
}
