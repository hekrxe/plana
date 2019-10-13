package com.hekrxe.hbase.rest;

import com.hekrxe.hbase.dao.UserDAO;
import com.hekrxe.hbase.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author tanhuayou on 2019/10/13
 */
@RestController
public class HelloRest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDAO userDAO;

    private int count = 0;


    @GetMapping("/hi")
    public String hello() {
        logger.info("Hello!");
        return "hello";
    }

    @GetMapping("/doAction")
    public Object action(@RequestParam("phone") String phone) {
        String name = "xiaoming";
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        if ((count & 1) == 0) {
            user.setSex((short) 1);
        } else {
            user.setSex((short) 0);
        }
        user.setAge(count);
        user.setEmail(phone);

        userDAO.batchInsert(Collections.singletonList(user));
        count++;

        return userDAO.queryAll(name);
    }
}
