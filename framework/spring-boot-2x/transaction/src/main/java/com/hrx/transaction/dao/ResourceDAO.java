
package com.hrx.transaction.dao;

import com.hrx.transaction.model.Resource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

/**
 * DAO
 * 2018-03-05 11:15:30
 *
 * @author KFC
 */
public interface ResourceDAO extends DAO {
    String TABLE = " pigeon_resource ";
    String NONE_ID_COLUMNS = " app_id, staff_id, name, size, url, db_update_time, db_create_time ";
    String NONE_ID_FIELDS = "(#{appId}, #{staffId}, #{name}, #{size}, #{url}, #{dbUpdateTime}, #{dbCreateTime})";


    @Insert(INSERT + INTO + TABLE + "(" + NONE_ID_COLUMNS + ")" + VALUES + NONE_ID_FIELDS)
    @Options(useGeneratedKeys = true)
    int insert(Resource resource) throws DataAccessException;

    @Select(SELECT + ID + NONE_ID_COLUMNS + FROM + TABLE + WHERE + "app_id=#{appId} AND id=#{id}")
    Resource queryById(@Param("appId") Long appId, @Param("id") Long id) throws DataAccessException;
}