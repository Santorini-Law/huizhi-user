package com.zhihui.user.domain.handler;

import com.zhihui.user.domain.enums.LoginTypeEnum;
import com.zhihui.user.domain.enums.RegisterSourceEnum;
import com.zhihui.utils.handler.BaseEnumTypeHandler;

/**
 * 登陆类型Handler
 *
 * @author LDZ
 * @date 2020-03-11 16:00
 */
public class LoginTypeHandler extends BaseEnumTypeHandler<LoginTypeEnum> {

    @Override
    public int getSaveDatabaseValue(LoginTypeEnum enumType) {
        return enumType.getCode();
    }

    @Override
    public LoginTypeEnum parseDatabaseValue(int value) {
        return LoginTypeEnum.getLoginTypeEnumByCode(value);
    }
}
