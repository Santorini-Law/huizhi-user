package com.zhihui.user.service.api;

import com.zhihui.user.domain.UserDO;

import java.util.List;

/**
 * @author LDZ
 * @date 2019-09-12 13:54
 */
public interface IUserService {


    String getUserNameById(Long id);

    String getUserNameByBB(Integer a, String bb);


    List<UserDO> getUserListByOffset(Integer offset);
}
