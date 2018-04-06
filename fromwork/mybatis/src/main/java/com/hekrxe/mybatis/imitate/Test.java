package com.hekrxe.mybatis.imitate;

import com.hekrxe.mybatis.dao.UserDAO;
import com.hekrxe.mybatis.model.User;

/**
 * User: tanhuayou
 * Date: 2018/4/6
 */
public class Test {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);

        User user = new User().setName("name").setAge(1212).setPassword("asdas");
        Integer insert = userDAO.insert(user);
        System.out.println(insert);
    }
}
