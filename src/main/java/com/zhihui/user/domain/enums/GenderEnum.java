package com.zhihui.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 性别
 *
 * @author LDZ
 * @date 2020-03-11 16:03
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {

    /**
     * 女性
     */
    FEMALE(0),

    /**
     * 男性
     */
    MALE(1);

    /**
     * code
     */
    private int code;


    private static Map<Integer, GenderEnum> GENDER_ENUM_MAP;

    static {
        GENDER_ENUM_MAP = Arrays.stream(GenderEnum.values()).collect(Collectors.toMap(GenderEnum::getCode, Function.identity()));
    }

    /**
     * 根据code 获取性别
     *
     * @param code code
     * @return 性别
     */
    public static GenderEnum getGenderEnumByCode(int code) {
        return GENDER_ENUM_MAP.getOrDefault(code, null);
    }

}
