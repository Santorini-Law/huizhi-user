package com.zhihui.user.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 默认进行ENUM 和 数据库的int 转换,如果需要String类型 可以进行拓展
 *
 * @author LDZ
 * @date 2020-03-11 15:50
 */
public abstract class BaseEnumTypeHandler<T extends Enum> extends BaseTypeHandler<T> {

    /**
     * 从 enum 到数据库的值
     *
     * @param enumType 值
     * @return code
     */
    public abstract int getSaveDatabaseValue(T enumType);

    /**
     * 解析从数据库到enum 类型
     *
     * @param value code
     * @return 值
     */
    public abstract T parseDatabaseValue(int value);


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, getSaveDatabaseValue(parameter));
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parseDatabaseValue(rs.getInt(columnName));
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseDatabaseValue(rs.getInt(columnIndex));
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseDatabaseValue(cs.getInt(columnIndex));
    }
}
