package com.zhihui.user.handler.bean;

import org.apache.ibatis.type.LocalDateTypeHandler;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * @author LDZ
 * @date 2020-03-12 13:36
 */
public class CustomLocalDateTypeHandler extends LocalDateTypeHandler {

    @Override
    public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Date date = (Date) rs.getObject(columnName);
        return Optional.ofNullable(date).map(Date::toLocalDate).orElse(null);
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Date date = (Date) rs.getObject(columnIndex);
        return Optional.ofNullable(date).map(Date::toLocalDate).orElse(null);
    }

    @Override
    public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Date date = (Date) cs.getObject(columnIndex);
        return Optional.ofNullable(date).map(Date::toLocalDate).orElse(null);
    }
}
