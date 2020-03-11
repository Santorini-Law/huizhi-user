package com.zhihui.user.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 用户基础额外信息
 *
 * @author LDZ
 * @date 2020-03-11 16:07
 */
@Data
public class UserBaseExtraDO {

    /**
     * 身高 cm
     */
    private BigDecimal stature;

    /**
     * 体重 kg
     */
    private BigDecimal weight;

}
