package com.hekrxe.mybatis.imitate;


/**
 * User: tanhuayou
 * Date: 2018/4/6
 */
public class SqlSession {
    protected MapperRegistry mapperRegistry;

    public SqlSession(MapperRegistry mapperRegistry) {
        System.out.println("创建了一个 SqlSession");
        this.mapperRegistry = mapperRegistry;
    }

    public <T> T getMapper(Class<T> type) {
        System.out.println("得到一个代理对象前");
        return mapperRegistry.getMapper(type, this);
    }

    // 其它的各种方法
}
