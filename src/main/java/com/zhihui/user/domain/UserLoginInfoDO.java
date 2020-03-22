package com.zhihui.user.domain;

import com.zhihui.user.domain.enums.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author LDZ
 * @date 2020-03-06 18:18
 */
@Data
public class UserLoginInfoDO {

    /**
     * 用户ID
     */
    Long uid;

    /**
     * 登陆方式
     */
    LoginTypeEnum loginType;

    /**
     * 属性
     */
    String property;

    /**
     * 登陆信息
     */
    String loginValue;

    /**
     * 有效
     * 0 无效
     * 1 有效
     */
    Integer userLoginInfoStatus;

    /**
     * 创建时间
     */
    LocalDateTime createTime;

    /**
     * 更新时间
     */
    LocalDateTime updateTime;

}
