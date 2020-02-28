package com.zhihui.user.dao;

import com.zhihui.user.domain.GrayFeatureDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class GrayDAOTest {


    @Resource
    GrayDAO grayDAO;

    @Test
    void testGetGrayFeatureByGrayId() {
        GrayFeatureDO grayFeatureByGrayId = grayDAO.getGrayFeatureByGrayId(200L);
        System.out.println(grayFeatureByGrayId);
    }
}