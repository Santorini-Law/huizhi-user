package com.zhihui.user.vo;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author LDZ
 * @date 2020-02-28 19:21
 */
@Data
public class GrayExecuteRequestVO extends GrayCheckRequestVO {

    /**
     * 新旧逻辑所在对象
     */
    private Object object;

    /**
     * 旧逻辑方法
     */
    private String oldLogicMethod;

    /**
     * 旧逻辑方法参数
     */
    private Object[] oldParams;

    /**
     * 新逻辑方法
     */
    private String newLogicMethod;

    /**
     * 新逻辑方法参数
     */
    private Object[] newParams;
}
