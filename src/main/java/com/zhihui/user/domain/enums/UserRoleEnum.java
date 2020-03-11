package com.zhihui.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
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
public enum UserRoleEnum {

    /**
     * 正常用户
     */
    NORMAL(0),

    /**
     * 黑名单
     */
    BLACK(1),

    /**
     * 内部用户
     */
    INNER(2);

    /**
     * code
     */
    private int code;


    private static Map<Integer, UserRoleEnum> USER_ROLE_ENUM_MAP;

    static {
        USER_ROLE_ENUM_MAP = Arrays.stream(UserRoleEnum.values()).collect(Collectors.toMap(UserRoleEnum::getCode, Function.identity()));
    }

    /**
     * 根据code 获取性别
     *
     * @param code code
     * @return 性别
     */
    public static UserRoleEnum getUserRoleEnumByCode(int code) {
        return USER_ROLE_ENUM_MAP.getOrDefault(code, null);
    }

}
