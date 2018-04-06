package com.hekrxe.mybatis.imitate;


import com.hekrxe.mybatis.dao.UserDAO;

/**
 * User: tanhuayou
 * Date: 2018/4/6
 */
public class SqlSessionFactoryBuilder {
    // 相当于 org.apache.ibatis.session.Configuration
    // 简单起见 直接用 MapperRegistry 替代，本次研究主要是运行流程，先不关心MyBatis解析配置文件的过程
    private MapperRegistry registry = new MapperRegistry();


    public SqlSessionFactory build() {
        // 即是解析配置文件的过程
        registry.addMapper(UserDAO.class);
        return new SqlSessionFactory(registry);
    }
}
