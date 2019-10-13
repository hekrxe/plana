package com.hekrxe.hbase.dao;

import com.hekrxe.hbase.model.User;
import com.hekrxe.hbase.utils.BytesUtils;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * #在命名空间ns1下，创建表t1，其中有一个列族f1，f1的版本数为5
 * hbase> create 'ns1:t1', {NAME => 'f1', VERSIONS => 5}
 *
 * #在默认命名空间下，创建表t1，有三个列族f1,f2,f3
 *   hbase> create 't1', {NAME => 'f1'}, {NAME => 'f2'}, {NAME => 'f3'}
 * #等价于
 *   hbase> create 't1', 'f1', 'f2', 'f3'
 *
 *   create 'hbase:user',{NAME => 'usr_fml', VERSIONS => 2}
 * @author tanhuayou on 2019/10/13
 */
@Repository
public class UserDAO implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private String tableName = "user";
    private byte[] family = Bytes.toBytes("usr_fml");
    private List<Field> fields;

    public int batchInsert(List<User> users) {
        List<Put> puts = users.stream().map(user -> {
            Put put = new Put(rowKey(user.getName(), user.getPhone()));
            fields.forEach(field -> put.addColumn(family, Bytes.toBytes(field.getName()), BytesUtils.encode(user, field)));
            return put;
        }).collect(Collectors.toList());

        return hbaseTemplate.execute(tableName, table -> {
            table.put(puts);
            return puts.size();
        });
    }

    public List<User> queryAll(String name) {
        Scan scan = new Scan();
        scan.setStartRow(rowKey(name, "!"));
        scan.setStopRow(rowKey(name, "{"));
        return hbaseTemplate.find(tableName, scan, results -> {
            List<User> users = new ArrayList<>();
            results.forEach(result -> {
                User user = new User();
                fields.forEach(field -> BytesUtils.decode(user, field, result.getValue(family, Bytes.toBytes(field.getName()))));
                users.add(user);
            });
            return users;
        });
    }


    private byte[] rowKey(String name, String phone) {
        return Bytes.toBytes(name + "_" + phone);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Field> fs = new ArrayList<>();
        ReflectionUtils.doWithFields(User.class, field -> {
            field.setAccessible(true);
            logger.info(field.getName() + "\t" + field.getType().getName());
            fs.add(field);
        });
        fields = fs;

    }
}
