package com.example.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.jetbrains.annotations.*;

import java.beans.PropertyVetoException;
import java.sql.*;

public class DBUtil implements AutoCloseable {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private DBUtil() {
    }

    @NotNull
    public static DBUtil dbUtil(String dbName, String user, String password) {
        return dbUtil("localhost", 3306, dbName, user, password);
    }

    @NotNull
    public static DBUtil dbUtil(String hostName, int port, String dbName, String user, String password) {
        var dbutil = new DBUtil();
        dbutil.getConnection(hostName, port, dbName, user, password);
        return dbutil;
    }

    /**
     * 得到数据库连接
     */
    private void getConnection(String hostName, int port, String dbName, String user, String password) {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        StringBuilder DBUrlBuffer = new StringBuilder("jdbc:mysql://localhost:3306/?&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
//        DBUrlBuffer.insert(28, dbName);
        // 注册驱动
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
//            dataSource.setJdbcUrl(DBUrlBuffer.toString());
            dataSource.setJdbcUrl("jdbc:mysql://" + hostName + ":" + port + "/" + dbName + "?&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
            dataSource.setUser(user);
            dataSource.setPassword(password);
            dataSource.setMaxPoolSize(40);
            dataSource.setMinPoolSize(2);
            dataSource.setInitialPoolSize(10);
            dataSource.setMaxStatements(100);

            // 获得数据库连接
            conn = dataSource.getConnection();
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace(System.err);
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
    public ResultSet executeQuery(String preparedSql, String... param) {
        if (conn != null) {
            // 处理SQL，执行SQL
            try {
                // 得到prepareStatement对象
                pstmt = conn.prepareStatement(preparedSql);
                if (pstmt != null && param.length > 0) {
                    for (int i = 0; i < param.length; i++) {
                        // 为预编译sql设置参数
                        pstmt.setString(i + 1, param[i]);
                    }
                    // 执行SQL语句
                    rs = pstmt.executeQuery();
                }
            } catch (SQLException e) {
                // 处理SQLException异常
                e.printStackTrace(System.err);
            }
        }
        return rs;
    }

    /**
     * 执行SQL语句，可以进行除查询以外的增、删、改的操作。
     */
    public int executeUpdate(String preparedSql, String... param) {
        int num = 0;

        if (conn != null) {
            // 处理SQL，执行SQL
            try {
                // 得到prepareStatement对象
                pstmt = conn.prepareStatement(preparedSql);
                if (pstmt != null) {
                    for (int i = 0; i < param.length; i++) {
                        // 为预编译sql设置参数
                        pstmt.setString(i + 1, param[i]);
                    }
                    // 执行SQL语句
                    num = pstmt.executeUpdate();
                }
            } catch (SQLException e) {
                // 处理SQLException异常
                e.printStackTrace(System.err);
            }
        }
        return num;
    }
}
