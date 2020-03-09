package com.zhihui.user.dao;

import com.zhihui.user.config.dynamic.DynamicDataSourceContextHolder;
import com.zhihui.user.domain.GrayFeatureDO;
import com.zhihui.user.domain.UserBaseDO;
import com.zhihui.user.domain.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ProductDaoTest {

    @Resource
    UserDAO userDAO;

    @Resource
    UserBaseDAO userBaseDAO;

    @Resource
    GrayDAO grayDAO;

    @Test
    void testUserDao() {
        System.out.println(DynamicDataSourceContextHolder.getDataSourceKey());
        List<UserBaseDO> all = userBaseDAO.findAll();
        for (UserBaseDO userBaseDO : all) {
            System.out.println(userBaseDO);
        }
        DynamicDataSourceContextHolder.useZhihuiDataSource();
        GrayFeatureDO grayFeatureByGrayId = grayDAO.getGrayFeatureByGrayId(200L);
        System.out.println(grayFeatureByGrayId);
        DynamicDataSourceContextHolder.useZebraDataSource();
        UserDO userDO = userDAO.select(39L);
        System.out.println(userDO.getMobile());
    }

}