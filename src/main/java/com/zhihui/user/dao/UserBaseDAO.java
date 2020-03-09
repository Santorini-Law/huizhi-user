package com.zhihui.user.dao;

import com.zhihui.user.domain.UserBaseDO;
import org.apache.ibatis.annotations.Mapper;

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
     * 获取所有
     *
     * @return 用户信息
     */
    List<UserBaseDO> getUserBaseByUid(Long uid);



}
