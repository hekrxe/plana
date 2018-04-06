package com.hekrxe.mybatis.imitate;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * User: tanhuayou
 * Date: 2018/4/6
 */
public class MapperProxy<T> implements InvocationHandler {
    private final Class<T> mapperInterface;
    private final SqlSession sqlSession;

    public MapperProxy(Class<T> mapperInterface, SqlSession sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        Object result = new MapperMethod(mapperInterface, sqlSession).execute();
        System.out.println(result);
        // TODO 返回值是上面这一行代码的返回值。 这里这样返回是为了不出现 NPE
        return method.getReturnType().getInterfaces();
    }
}
