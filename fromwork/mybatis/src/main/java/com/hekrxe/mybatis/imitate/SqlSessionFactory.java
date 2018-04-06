package com.hekrxe.mybatis.imitate;


/**
 * User: tanhuayou
 * Date: 2018/4/6
 */
public class SqlSessionFactory {
    private MapperRegistry registry;


    public SqlSessionFactory(MapperRegistry registry) {
        this.registry = registry;
    }

    public SqlSession openSession() {
        return new SqlSession(registry);
    }
}