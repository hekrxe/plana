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
        System.out.println("MapperProxy: 代理对象处理器被创建");
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        System.out.println("当前用户正在执行的方法：" + method.getName());
        System.out.println("其接口是：" + mapperInterface.getName());
        System.out.println(mapperInterface.getName() + "." + method.getName() + ": 是一个id,用于到对应的mapper.xml文件中得到具体的SQL语句");
        Object result = new MapperMethod(mapperInterface, sqlSession).execute();
        System.out.println("真正的执行结果： " + result);
        // TODO 返回值是上面这一行代码的返回值。 这里这样返回是为了不出现 NPE
        return method.getReturnType().getConstructor(int.class).newInstance(999);
    }
}
