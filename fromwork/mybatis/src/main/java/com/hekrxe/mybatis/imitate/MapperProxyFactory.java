package com.hekrxe.mybatis.imitate;

import java.lang.reflect.Proxy;

/**
 * User: tanhuayou
 * Date: 2018/4/6
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @SuppressWarnings("unchecked")
    public T newInstance(SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, new MapperProxy<>(mapperInterface, sqlSession));
    }
}
