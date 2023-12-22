package com.system.DAO.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;


public class PooledDBConn {
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();
    static {
        try (Scanner sc = new Scanner(Objects.requireNonNull(PooledDBConn.class.getClassLoader().getResourceAsStream("config.yml")))) {
            // 注册驱动
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            // 设置连接参数
            dataSource.setJdbcUrl(sc.nextLine().split(": ")[1]);
            dataSource.setUser(sc.nextLine().split(": ")[1]);
            dataSource.setPassword(sc.nextLine().split(": ")[1]);
            dataSource.setMaxPoolSize(40);
            dataSource.setMinPoolSize(2);
            dataSource.setInitialPoolSize(10);
            dataSource.setMaxStatements(100);
        } catch (PropertyVetoException e) {
            e.printStackTrace(System.err);
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        // 获得数据库连接
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
