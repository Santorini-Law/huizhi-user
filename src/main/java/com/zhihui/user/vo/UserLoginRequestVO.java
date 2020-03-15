package com.zhihui.user.vo;

import lombok.Data;

/**
 * @author LDZ
 * @date 2020-03-15 12:53
 */
@Data
public class UserLoginRequestVO {

    /**
     * CODE
     */
    private String code;

    /**
     * 来源
     */
    private String source;
}
