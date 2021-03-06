package com.zhihui.user.dao;

import com.zhihui.user.config.dynamic.DynamicDataSourceContextHolder;
import com.zhihui.user.domain.GrayFeatureDO;
import com.zhihui.user.domain.UserBaseDO;
import com.zhihui.user.domain.UserDO;
import com.zhihui.user.domain.enums.GenderEnum;
import com.zhihui.user.domain.enums.RegisterSourceEnum;
import com.zhihui.user.domain.enums.UserRoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class ProductDaoTest {

    @Resource
    UserDAO userDAO;

    @Resource
    UserBaseDAO userBaseDAO;

    @Resource
    GrayDAO grayDAO;

    @Test
    void testUserDao() {
        log.info("current datasource key is {}", DynamicDataSourceContextHolder.getDataSourceKey());
        UserBaseDO userBaseDO = userBaseDAO.getUserBaseByUid(127L);
        log.info("user base info is {}", userBaseDO);
        DynamicDataSourceContextHolder.useZhihuiDataSource();
        GrayFeatureDO grayFeatureByGrayId = grayDAO.getGrayFeatureByGrayId(200L);
        log.info("gray feature is {}", grayFeatureByGrayId);
        DynamicDataSourceContextHolder.useZebraDataSource();
        UserDO userDO = userDAO.getUserByUid(39L);
        log.info("user info is {}", userDO);
    }

    @Test
    public void testInsertUserBase() {
        UserBaseDO userBaseDO = new UserBaseDO();
        userBaseDO.setUserRole(UserRoleEnum.NORMAL);
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
        userBaseDO.setUid(10086L);
        userBaseDO.setUserName("userName" + 10086);
        userBaseDO.setNickName("nickName" + 10086);

        userBaseDAO.insert(userBaseDO);
    }
}