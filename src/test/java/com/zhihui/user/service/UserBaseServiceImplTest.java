package com.zhihui.user.service;

import com.zhihui.user.dao.UserBaseDAO;
import com.zhihui.user.domain.UserBaseDO;
import com.zhihui.user.domain.enums.GenderEnum;
import com.zhihui.user.domain.enums.RegisterSourceEnum;
import com.zhihui.user.service.api.IUserBaseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class UserBaseServiceImplTest {

    @Resource
    IUserBaseService userBaseService;

    @Test
    void batchInsertUserBaseInfo() {

        List<UserBaseDO> userBaseDOList = new ArrayList<>();
        for (long i = 64; i < 129; i++) {
            UserBaseDO userBaseDO = new UserBaseDO();
            userBaseDO.setUserRole(1);
            userBaseDO.setRegisterSource(RegisterSourceEnum.MOBILE);
            userBaseDO.setGender(GenderEnum.FEMALE);
            userBaseDO.setBirthday(LocalDate.now());
            userBaseDO.setMobile("13123123123");
            userBaseDO.setMobileBindTime(LocalDateTime.now());
            userBaseDO.setEmail("");
            userBaseDO.setEmailBindTime(LocalDateTime.now());
            userBaseDO.setCreateTime(LocalDateTime.now());
            userBaseDO.setUpdateTime(LocalDateTime.now());
            userBaseDO.setRealName("");
            userBaseDO.setIdCard("");
            userBaseDO.setBaseExtra(null);
            userBaseDO.setUid(i);
            userBaseDO.setUserName("userName" + i);
            userBaseDO.setNickName("nickName" + i);
            userBaseDOList.add(userBaseDO);
        }
        System.out.println(userBaseDOList);
        userBaseService.batchInsertUserBaseInfo(userBaseDOList);

    }

    @Test
    void washData() {

    }


}