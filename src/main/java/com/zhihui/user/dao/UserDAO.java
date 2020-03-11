package com.zhihui.user.dao;

import com.zhihui.user.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserDO mapper for operate data of products table
 *
 * @author LDZ
 * @date 2020-03-09 23:10
 */
@Mapper
public interface UserDAO {

    /**
     * 根据uid获取用户信息
     *
     * @param uid uid
     * @return 用户信息
     */
    UserDO getUserByUid(@Param("uid") long uid);


    /**
     * 每次拿1000个用户信息
     *
     * @param offset 偏移量
     * @return 1000个用户信息
     */
    List<UserDO> getUserListByOffset(Integer offset);


    List<String> getAllMobile();


}
