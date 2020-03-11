package com.zhihui.user.domain.handler;

import com.zhihui.user.domain.enums.UserRoleEnum;
import com.zhihui.user.handler.BaseEnumTypeHandler;

/**
 * @author LDZ
 * @date 2020-03-11 16:00
 */
public class UserRoleHandler extends BaseEnumTypeHandler<UserRoleEnum> {

    @Override
    public int getSaveDatabaseValue(UserRoleEnum enumType) {
        return enumType.getCode();
    }

    @Override
    public UserRoleEnum parseDatabaseValue(int value) {
        return UserRoleEnum.getUserRoleEnumByCode(value);
    }
}
