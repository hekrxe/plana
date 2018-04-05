package com.hekrxe.mybatis;

import com.hekrxe.mybatis.dao.UserDAO;
import com.hekrxe.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * User: tanhuayou
 * Date: 2018/4/5
 */
public class Main {
    public static void main(String[] args) {
        insert("thy", 18, "1212121");
    }

    private static void insert(String name, Integer age, String password) {
        SqlSessionFactory sessionFactory = getSessionFactory();
        SqlSession sqlSession = sessionFactory.openSession();
        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);

        User user = new User().setName(name).setAge(age).setPassword(password);
        int insert = userDAO.insert(user);
        System.out.println("insert: " + insert);

        sqlSession.commit();
        sqlSession.close();

    }

    //Mybatis 通过SqlSessionFactory获取SqlSession, 然后才能通过SqlSession与数据库进行交互
    private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "config.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
