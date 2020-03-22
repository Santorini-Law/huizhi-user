package com.zhihui.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 登陆类型
 *
 * @author LDZ
 * @date 2020/3/22 8:07 下午
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

    /**
     * 未知
     */
    GITHUB(1);

    /**
     * code
     */
    private int code;


    private static Map<Integer, LoginTypeEnum> LOGIN_TYPE_ENUM_MAP;

    static {
        LOGIN_TYPE_ENUM_MAP = Arrays.stream(LoginTypeEnum.values()).collect(Collectors.toMap(LoginTypeEnum::getCode, Function.identity()));
    }

    /**
     * 根据code 获取性别
     *
     * @param code code
     * @return 性别
     */
    public static LoginTypeEnum getLoginTypeEnumByCode(int code) {
        return LOGIN_TYPE_ENUM_MAP.getOrDefault(code, null);
    }

}
