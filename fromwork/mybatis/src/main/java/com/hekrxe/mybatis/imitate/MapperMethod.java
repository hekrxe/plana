package com.hekrxe.mybatis.imitate;

/**
 * MapperMethod 是对JDBC的封装
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
        System.out.println("执行 SQL语句。");
        return null;
    }
}
