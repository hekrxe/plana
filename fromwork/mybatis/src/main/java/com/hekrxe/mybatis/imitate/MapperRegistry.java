package com.hekrxe.mybatis.imitate;

import java.util.HashMap;
import java.util.Map;

/**
 * User: tanhuayou
 * Date: 2018/4/6
 */
public class MapperRegistry {
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new UnsupportedOperationException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error getting mapper instance. Cause: " + e, e);
        }
    }


    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            System.out.println("MapperRegistry：解析完配置文件时，注册一个接口对应的 MapperProxyFactory ，以便后期构造一个代理对象");
            knownMappers.put(type, new MapperProxyFactory<T>(type));
        }
    }
}
