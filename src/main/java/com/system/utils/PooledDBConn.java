package com.system.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.jetbrains.annotations.Nullable;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static com.system.JavaFX.view.ProjectApplication.configData;


public class PooledDBConn {
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();
    static {
        try {
            // 获取顶层键 "dbConfig" 的值
            @SuppressWarnings("unchecked")
            Map<String, Object> dbConfig = (Map<String, Object>) configData.get("dbConfig");
            // 读取子属性
            String jdbcDriverClass = (String) dbConfig.get("JDBCDriverClass");
            String jdbcUrl = (String) dbConfig.get("JDBCurl");
            String dbUserName = (String) dbConfig.get("userName");
            String dbPassword = (String) dbConfig.get("passWord");

            // 注册驱动
            dataSource.setDriverClass(jdbcDriverClass);
            // 设置连接参数
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUser(dbUserName);
            dataSource.setPassword(dbPassword);
            dataSource.setMaxPoolSize(40);
            dataSource.setMinPoolSize(2);
            dataSource.setInitialPoolSize(10);
            dataSource.setMaxStatements(100);
        } catch (PropertyVetoException e) {
            e.printStackTrace(System.err);
        }
    }
    @Nullable
    public static Connection getConnection() throws SQLException {
        // 获得数据库连接
        return dataSource.getConnection();
    }
}
