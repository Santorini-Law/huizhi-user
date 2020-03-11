package com.zhihui.user.service;

import com.zhihui.user.config.dynamic.DynamicDataSourceContextHolder;
import com.zhihui.user.dao.UserBaseDAO;
import com.zhihui.user.domain.UserBaseDO;
import com.zhihui.user.service.api.IUserBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @author LDZ
 * @date 2020-03-11 14:45
 */
@Service
@Slf4j
public class UserBaseServiceImpl implements IUserBaseService {

    @Qualifier("batchInsert")
    @Resource
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    UserBaseDAO userBaseDAO;

    @Override
    public void batchInsertUserBaseInfo(List<UserBaseDO> userBaseList) {
        log.info("batch insert {} user base info... ", userBaseList.size());
        DynamicDataSourceContextHolder.useShardingDataSource();
        try {
            for (UserBaseDO userBaseDO : userBaseList) {
                log.info("?? {}", userBaseDO);
                int a = userBaseDAO.insert(userBaseDO);
                log.info("*** {}", userBaseDO);
                log.info("insert {} 条", a);

            }

        } catch (Exception e) {
            log.error("insert error", e);
        }

    }

    @Override
    public void insertUserBaseInfo(UserBaseDO userBaseDO) {
        DynamicDataSourceContextHolder.useShardingDataSource();
        userBaseDAO.insert(userBaseDO);
    }

    @Override
    public UserBaseDO getUserBaseInfoByMobile(String mobile) {
        DynamicDataSourceContextHolder.useShardingDataSource();
        return userBaseDAO.getUserBaseByMobile(mobile);
    }

    @Override
    public UserBaseDO getUserBaseInfoByUid(Long uid) {
        DynamicDataSourceContextHolder.useShardingDataSource();
        return userBaseDAO.getUserBaseByUid(uid);
    }

    @Override
    public List<UserBaseDO> getUserBaseInfoByEmail(String email) {
        DynamicDataSourceContextHolder.useShardingDataSource();
        return userBaseDAO.getUserBaseByEmail(email);
    }
}
