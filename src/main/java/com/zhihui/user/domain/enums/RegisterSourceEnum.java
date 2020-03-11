package com.zhihui.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 注册来源
 *
 * @author LDZ
 * @date 2020-03-11 15:43
 */
@Getter
@AllArgsConstructor
public enum RegisterSourceEnum {

    /**
     * 初始化数据
     */
    INIT(0),

    /**
     *
     */
    MOBILE(1),

    /**
     * 邮箱
     */
    EMAIL(2);


    /**
     * code
     */
    private Integer registerSourceCode;


    private static Map<Integer, RegisterSourceEnum> REGISTER_SOURCE_ENUM_MAP = new HashMap<>(4);

    static {
        REGISTER_SOURCE_ENUM_MAP = Arrays.stream(RegisterSourceEnum.values()).collect(Collectors.toMap(RegisterSourceEnum::getRegisterSourceCode, Function.identity()));
    }

    /**
     * 根据code 获取注册途径
     *
     * @param registerSourceCode code
     * @return 注册途径
     */
    public static RegisterSourceEnum getRegisterSourceEnumByCode(int registerSourceCode) {
        return REGISTER_SOURCE_ENUM_MAP.getOrDefault(registerSourceCode, null);
    }


}
