package com.zhihui.user.service;

import com.zhihui.user.service.api.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author LDZ
 * @date 2018/9/28 上午11:00
 */
@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Autowired
    RedissonClient redissonClient;

    @Override
    public <T> void saveValue(String key, T value) {
        try {
            RBucket<T> rBucket = redissonClient.getBucket(key);
            rBucket.set(value);
        } catch (Exception e) {
            log.error("error:_{}", e);
        }

    }

    @Override
    public <T> void saveValue(String key, T value, Long seconds) {
        try {
            RBucket<T> rBucket = redissonClient.getBucket(key);
            rBucket.set(value, seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("error:_{}", e);
        }
    }

    @Override
    public <T> T getValue(String key) {
        T result = null;
        try {
            RBucket<T> rBucket = redissonClient.getBucket(key);
            result = rBucket.get();
        } catch (Exception e) {
            log.error("error:_{}", e);
        }
        return result;
    }



    @Override
    public void saveInvitationCodeImage(String key, String value) {
        saveValue("INVITATION_CODE:" + key, value, 24 * 60 * 60L);
    }

    @Override
    public String getInvitationCodeImage(String key) {
        return getValue("INVITATION_CODE:" + key);
    }

    @Override
    public void saveUserDeviceType(Long uid, String device) {
        // 存12小时
        saveValue("USER_DEVICE:" + uid, device, 12 * 60 * 60L);
    }

    @Override
    public String getUserDeviceType(Long uid) {
        return getValue("USER_DEVICE:" + uid);
    }
}
