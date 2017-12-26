package com.hekrxe.mybatis.ch2;

import com.hekrxe.mybatis.ch2.dao.UserDAO;
import com.hekrxe.mybatis.ch2.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author hztanhuayou
 * @date 2017/12/4
 */
public class Main {
    public static void main(String[] args) {
        String conf = "ch2/conf.xml";
        InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(conf);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 得到接口的实现类,此类是Mybatis通过Proxy动态代理出来的
        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);

        List<User> users = userDAO.queryAll();
        for (User usr : users) {
            System.out.println(usr);
        }
//        http://www.cnblogs.com/xdp-gacl/p/4264425.html
    }
}
