package com.zhihui.user.service;

import com.zhihui.user.domain.UserDO;
import com.zhihui.user.service.api.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Resource
    IUserService userService;

    @Test
    void getUserListByOffset() {
        List<UserDO> userListByOffset = userService.getUserListByOffset(0);

        for (UserDO userDO : userListByOffset) {
            log.info(userDO.toString());
        }

    }
}