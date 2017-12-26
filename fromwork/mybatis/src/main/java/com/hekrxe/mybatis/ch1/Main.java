package com.hekrxe.mybatis.ch1;

import com.hekrxe.mybatis.ch1.model.User;
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
        String resource = "ch1/conf_ch1.xml";
        InputStream resourceInput = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        // 构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceInput);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        // mapper文件中的标识符
        String statement = "com.hekrxe.mybatis.ch1.dao.UserDAO.getUserById";
        User user = sqlSession.selectOne(statement, 1L);
        System.out.println(user);

        user.setName("hekrxe");
        user.setAge(18);
        statement = "com.hekrxe.mybatis.ch1.dao.UserDAO.addUser";
        sqlSession.insert(statement, user);
        sqlSession.commit();

        System.out.println("\n============all==================");
        statement = "com.hekrxe.mybatis.ch1.dao.UserDAO.selectAll";
        List<User> users = sqlSession.selectList(statement);
        for (User us : users) {
            System.out.println(us);
            statement = "com.hekrxe.mybatis.ch1.dao.UserDAO.updateUser";
            us.setAge(us.getAge() + 1);
            us.setName(us.getName() + "-hi");
            sqlSession.update(statement, us);
            sqlSession.commit();
        }


        System.out.println("\n============all==================");
        statement = "com.hekrxe.mybatis.ch1.dao.UserDAO.selectAll";
        users = sqlSession.selectList(statement);
        for (User us : users) {
            System.out.println(us);
        }


        sqlSession.close();
    }
}
