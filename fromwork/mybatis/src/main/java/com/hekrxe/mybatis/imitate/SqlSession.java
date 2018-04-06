package com.hekrxe.mybatis.imitate;


/**
 * User: tanhuayou
 * Date: 2018/4/6
 */
public class SqlSession {
    protected MapperRegistry mapperRegistry;

    public SqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }

    // 其它的各种方法
}
