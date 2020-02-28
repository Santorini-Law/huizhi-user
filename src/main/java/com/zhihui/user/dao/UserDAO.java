package com.zhihui.user.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * user dao
 *
 * @author LDZ
 * @date 2019-09-10 12:23
 */
@Mapper
public interface UserDAO {

    String getUserNameById(long id);
}
