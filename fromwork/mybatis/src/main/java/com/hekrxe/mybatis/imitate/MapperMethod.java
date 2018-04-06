package com.hekrxe.mybatis.imitate;

/**
 * User: tanhuayou
 * Date: 2018/4/6
 */
public class MapperMethod {
    private final Class<?> mapperInterface;
    private final SqlSession sqlSession;

    public MapperMethod(Class<?> mapperInterface, SqlSession sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    // 解析并执行 sql
    public Object execute() {
        System.out.println(mapperInterface.getName());
        System.out.println(sqlSession.getClass().getName());
        return null;
    }
}
