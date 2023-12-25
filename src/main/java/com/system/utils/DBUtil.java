package com.system.utils;

import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class DBUtil implements AutoCloseable {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    /**
     * 从连接池中得到数据库连接
     */
    public DBUtil() {
        try {
            conn = PooledDBConn.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        // 如果rs不空，关闭rs
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        // 如果pstmt不空，关闭pstmt
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        // 如果conn不空，关闭conn
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    /**
     * 执行SQL语句，可以进行查询操作。
     */
    public ResultSet executeQuery(String preparedSql, Object... param) throws SQLException {
        if (conn != null) {
            // 得到prepareStatement对象
            pstmt = conn.prepareStatement(preparedSql);
            if (pstmt != null) {
                insertParams(param);
                // 执行SQL语句
                rs = pstmt.executeQuery();
            }
        }
        return rs;
    }

    private void insertParams(@NotNull Object... param) throws SQLException {
        for (int i = 0; i < param.length; i++) {
            // 为预编译sql设置参数
            if (param[i] instanceof String) {
                pstmt.setString(i + 1, (String) param[i]);
            } else if (param[i] instanceof Integer) {
                pstmt.setInt(i + 1, (int) param[i]);
            } else if (param[i] instanceof Double) {
                pstmt.setDouble(i + 1, (double) param[i]);
            }
        }
    }

    /**
     * 执行SQL语句，可以进行除查询以外的增、删、改的操作。
     */
    public int executeUpdate(String preparedSql, Object... param) throws SQLException {
        int num = 0;

        if (conn != null) {
            // 处理SQL，执行SQL
            // 得到prepareStatement对象
            pstmt = conn.prepareStatement(preparedSql);
            if (pstmt != null) {
                insertParams(param);
                // 执行SQL语句
                num = pstmt.executeUpdate();
            }
        }
        return num;
    }
}
