package com.zhihui.user.vo;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author LDZ
 * @date 2020-02-28 19:21
 */
@Data
public class GrayExecuteResponseVO {

    /**
     * 灰度结果
     */
    Boolean grayResult;

    /**
     * 执行返回结果
     */
    Object result;

}
