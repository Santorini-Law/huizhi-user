package com.zhihui.user.dao;

import com.zhihui.user.domain.UserBaseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LDZ
 * @date 2020-03-07 21:02
 */
@Mapper
public interface UserBaseDAO {

    /**
     * 插入用户基本信息
     *
     * @param userBaseDO 用户基础信息
     * @return 插入条数
     */
    int insert(UserBaseDO userBaseDO);

    /**
     * 根据uid获取用户信息
     *
     * @param uid uid
     * @return 用户信息
     */
    UserBaseDO getUserBaseByUid(Long uid);


}
