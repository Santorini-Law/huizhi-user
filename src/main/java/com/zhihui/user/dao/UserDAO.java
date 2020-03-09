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
    UserDO select(@Param("uid") long uid);
}
