package com.zhihui.user.dao;

import com.zhihui.user.domain.UserBaseDO;
import com.zhihui.user.domain.UserLoginInfoDO;
import com.zhihui.user.domain.enums.LoginTypeEnum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户登陆信息
 *
 * @author LDZ
 * @date 2020-03-07 21:02
 */
@Mapper
public interface UserLoginInfoDAO {

    /**
     * 插入用户三方登陆信息
     *
     * @param userLoginInfoDO
     * @return
     */
    int insert(UserLoginInfoDO userLoginInfoDO);

    /**
     * 根据uid获取用户信息
     *
     * @param uid uid
     * @return 用户信息
     */
    List<UserLoginInfoDO> getUserLoginInfoByUid(Long uid);

    /**
     * 根据属性获取用户三方登陆信息
     *
     * @author LDZ
     * @date 2020/3/22 8:04 下午
     */
    UserLoginInfoDO getUserLoginInfoByProperty(LoginTypeEnum loginType, String property, String loginValue);
}
