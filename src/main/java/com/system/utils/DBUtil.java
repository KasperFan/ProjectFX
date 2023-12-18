package com.system.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.jetbrains.annotations.NotNull;

import java.beans.PropertyVetoException;
import java.sql.*;

public class DBUtil implements AutoCloseable {
    protected String addSQL = "INSERT INTO `%s` (%s) VALUES (%s)";
    protected String updateSQL = "UPDATE `%s` SET %s WHERE %s";
    protected String deleteSQL = "DELETE FROM `%s` WHERE %s";
    protected String getSQL = "SELECT %s FROM `%s` %s";
    protected String sentence = "WHERE %s";
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;


    public DBUtil(String dbName, String user, String password) {
        this("localhost", 3306, dbName, user, password);
    }

    public DBUtil(String hostName, int port, String dbName, String user, String password) {
        this.getConnection(hostName, port, dbName, user, password);
    }


    @NotNull
    public static DBUtil dbUtil(String hostName, int port, String dbName, String user, String password) {
        return new DBUtil(hostName, port, dbName, user, password);
    }

    /**
     * 得到数据库连接
     */
    private void getConnection(String hostName, int port, String dbName, String user, String password) {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        // 注册驱动
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
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
    public ResultSet executeQuery(String preparedSql, Object... param) throws SQLException {
        if (conn != null) {
            // 得到prepareStatement对象
            pstmt = conn.prepareStatement(preparedSql);
            if (pstmt != null && param.length > 0) {
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
                // 执行SQL语句
                rs = pstmt.executeQuery();
            }
        }
        return rs;
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
                // 执行SQL语句
                num = pstmt.executeUpdate();
            }
        }
        return num;
    }
}
