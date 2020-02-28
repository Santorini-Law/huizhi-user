package com.zhihui.user.vo;

import lombok.Data;

/**
 * 灰度检测请求
 *
 * @author LDZ
 * @date 2020-02-28 12:49
 */
@Data
public class GrayCheckRequestVO {

    /**
     * 灰度id
     */
    private Long grayId;

    /**
     * 用户id
     */
    private Long uid;

}
