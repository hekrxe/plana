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
        System.out.println("一个 MapperProxyFactory 被创建，服务于接口 " + mapperInterface.getName());
    }

    @SuppressWarnings("unchecked")
    public T newInstance(SqlSession sqlSession) {
        System.out.println("用 MapperProxyFactory 创建一个代理对象：");
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, new MapperProxy<>(mapperInterface, sqlSession));
    }
}
