package com.zhihui.user.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 灰度
 *
 * @author LDZ
 * @date 2020-02-28 13:10
 */
@Data
public class GrayFeatureDO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 灰度id
     */
    private Long grayId;

    /**
     * 灰度名称
     */
    private String grayName;

    /**
     * 0 灰度关闭 直接返回false 走老逻辑
     * 1 灰度开启 进入灰度判断
     * 2 灰度关闭 直接返回true 走新逻辑
     */
    private Integer graySwitch;

    /**
     * 白名单
     */
    private String whiteList;

    /**
     * 黑名单 优先级高于白名单
     */
    private String blackList;

    /**
     * 灰度区间 左闭右开
     */
    private String grayInterval;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
