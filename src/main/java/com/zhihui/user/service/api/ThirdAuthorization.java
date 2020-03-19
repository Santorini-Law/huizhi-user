package com.zhihui.user.service.api;

/**
 * 三方认证
 *
 * @author LDZ
 * @date 2020-03-19 20:20
 */
public interface ThirdAuthorization {

    /**
     * 获取用户 uid
     *
     * @param thirdCode 三方code
     * @return uid
     */
    Long getUserId(String thirdCode);
}
