package com.zhihui.user.service.api;

import com.zhihui.user.domain.UserBaseDO;
import com.zhihui.user.domain.UserLoginInfoDO;
import com.zhihui.user.domain.enums.LoginTypeEnum;

import java.util.List;

/**
 * 用户基础信息
 *
 * @author LDZ
 * @date 2020-03-11 14:26
 */

public interface IUserBaseService {

    /**
     * 批量插入用户信息
     *
     * @param userBaseList 用户基础信息
     */
    void batchInsertUserBaseInfo(List<UserBaseDO> userBaseList);


    void insertUserBaseInfo(UserBaseDO userBaseDO);

    void insertUserLoginInfo(UserLoginInfoDO userLoginInfoDO);

    UserLoginInfoDO getUserLoginInfoDO(LoginTypeEnum loginType, String property, String loginValue);

    UserBaseDO getUserBaseInfoByMobile(String mobile);

    UserBaseDO getUserBaseInfoByUid(Long uid);


    List<UserBaseDO> getUserBaseInfoByEmail(String email);
}
