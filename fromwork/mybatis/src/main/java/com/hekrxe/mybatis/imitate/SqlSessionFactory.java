package com.hekrxe.mybatis.imitate;


/**
 * User: tanhuayou
 * Date: 2018/4/6
 */
public class SqlSessionFactory {
    private MapperRegistry registry;


    public SqlSessionFactory(MapperRegistry registry) {
        System.out.println("用解析完的配置文件创建一个 SqlSession工厂(SqlSessionFactory)，用于应用程序在启动（Spring+MyBatis）或运行时得到一个 SqlSession");
        this.registry = registry;
    }

    public SqlSession openSession() {
        System.out.println("用 SqlSessionFactory 生产一个SqlSession");
        return new SqlSession(registry);
    }
}