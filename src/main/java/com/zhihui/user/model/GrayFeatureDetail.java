package com.zhihui.user.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 灰度细节
 *
 * @author LDZ
 * @date 2020-02-28 14:47
 */
@Data
public class GrayFeatureDetail {


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
    private List<Long> whiteList;

    /**
     * 黑名单 优先级高于白名单
     */
    private List<Long> blackList;

    /**
     * 灰度区间 上限
     */
    private Integer grayIntervalCell;

    /**
     * 灰度区间 下限 可等于
     */
    private Integer grayIntervalFloor;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
