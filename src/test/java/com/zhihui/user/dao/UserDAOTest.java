package com.zhihui.user.dao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDAOTest {

    @Resource
    UserDAO userDAO;

    @Test
    void testUserDao() {
        String userNameById = userDAO.getUserNameById(1);
        System.out.println(userNameById);
    }
}
