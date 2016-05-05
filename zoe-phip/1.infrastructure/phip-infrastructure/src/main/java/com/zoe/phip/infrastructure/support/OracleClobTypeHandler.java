package com.zoe.phip.infrastructure.support;

import oracle.sql.CLOB;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhangwenbin on 2016/5/4.
 */
public final class OracleClobTypeHandler implements TypeHandler<Object> {

    @Override
    public void setParameter(PreparedStatement arg0, int arg1, Object arg2, JdbcType jdbcType) throws SQLException {
        CLOB clob = CLOB.getEmptyCLOB();
        clob.setString(1, (String) arg2);
        arg0.setClob(arg1, clob);
    }

    @Override
    public Object getResult(ResultSet arg0, String arg1) throws SQLException {
        CLOB clob = (CLOB) arg0.getClob(arg1);
        return (clob == null || clob.length() == 0) ? null : clob.getSubString((long) 1, (int) clob.length());
    }

    @Override
    public Object getResult(ResultSet arg0, int arg1) throws SQLException {
        return null;
    }

    @Override
    public Object getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
