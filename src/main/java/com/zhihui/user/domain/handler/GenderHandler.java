package com.zhihui.user.domain.handler;

import com.zhihui.user.domain.enums.GenderEnum;
import com.zhihui.user.domain.enums.RegisterSourceEnum;
import com.zhihui.user.handler.BaseEnumTypeHandler;

/**
 * @author LDZ
 * @date 2020-03-11 16:00
 */
public class GenderHandler extends BaseEnumTypeHandler<GenderEnum> {

    @Override
    public int getSaveDatabaseValue(GenderEnum enumType) {
        return enumType.getCode();
    }

    @Override
    public GenderEnum parseDatabaseValue(int value) {
        return GenderEnum.getGenderEnumByCode(value);
    }
}
