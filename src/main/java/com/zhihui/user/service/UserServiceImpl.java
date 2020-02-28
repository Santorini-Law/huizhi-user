package com.zhihui.user.service;

import com.zhihui.user.dao.UserDAO;
import com.zhihui.user.service.api.CacheService;
import com.zhihui.user.service.api.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LDZ
 * @date 2019-09-12 13:53
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserDAO userDAO;

    @Resource
    CacheService cacheService;

    @Override
    public String getUserNameById(Long id) {
        return "" + cacheService.getValue("a");
    }

    @Override
    public String getUserNameByBB(Integer a, String bb) {
        return a + bb;
    }


}
