package com.zhihui.user.service;

import com.zhihui.user.service.api.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CacheServiceImplTest {

    @Resource
    CacheService cacheService;

    @Test
    void saveValue() {

        cacheService.saveValue("a", 987);
    }

    @Test
    void saveValue1() {
    }

    @Test
    void getValue() {

        System.out.println("" + cacheService.getValue("a"));
    }

    @Test
    void saveInvitationCodeImage() {
    }

    @Test
    void getInvitationCodeImage() {
    }

    @Test
    void saveUserDeviceType() {
    }

    @Test
    void getUserDeviceType() {
    }
}