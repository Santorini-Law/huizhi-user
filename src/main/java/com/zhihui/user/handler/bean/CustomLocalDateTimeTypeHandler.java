package com.zhihui.user.handler.bean;

import com.alibaba.com.caucho.hessian.io.java8.LocalDateTimeHandle;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author LDZ
 * @date 2020-03-12 13:58
 */
public class CustomLocalDateTimeTypeHandler extends LocalDateTimeTypeHandler {

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp timestamp = (Timestamp) rs.getObject(columnName);
        return Optional.ofNullable(timestamp).map(Timestamp::toLocalDateTime).orElse(null);
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp timestamp = (Timestamp) rs.getObject(columnIndex);
        return Optional.ofNullable(timestamp).map(Timestamp::toLocalDateTime).orElse(null);
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp timestamp = (Timestamp) cs.getObject(columnIndex);
        return Optional.ofNullable(timestamp).map(Timestamp::toLocalDateTime).orElse(null);
    }

}
