package com.zhihui.user.domain.handler;

import com.zhihui.user.domain.enums.RegisterSourceEnum;
import com.zhihui.utils.handler.BaseEnumTypeHandler;

/**
 * @author LDZ
 * @date 2020-03-11 16:00
 */
public class RegisterSourceHandler extends BaseEnumTypeHandler<RegisterSourceEnum> {

    @Override
    public int getSaveDatabaseValue(RegisterSourceEnum enumType) {
        return enumType.getRegisterSourceCode();
    }

    @Override
    public RegisterSourceEnum parseDatabaseValue(int value) {
        return RegisterSourceEnum.getRegisterSourceEnumByCode(value);
    }
}
