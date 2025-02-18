package com.system.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.Yaml;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;


public class PooledDBConn {
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();
    static {
        try (InputStream inputStream = PooledDBConn.class.getClassLoader().getResourceAsStream("config.yaml")) {
            Yaml yaml = new Yaml();
            // 读取 YAML 文件并转换为 Map
            Map<String, Object> data = yaml.load(inputStream);
            // 获取顶层键 "dbConfig" 的值
            Map<String, Object> dbConfig = (Map<String, Object>) data.get("dbConfig");
            // 读取子属性
            String jdbcUrl = (String) dbConfig.get("JDBCurl");
            String dbUserName = (String) dbConfig.get("userName");
            String dbPassword = (String) dbConfig.get("passWord");

            // 注册驱动
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            // 设置连接参数
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUser(dbUserName);
            dataSource.setPassword(dbPassword);
            dataSource.setMaxPoolSize(40);
            dataSource.setMinPoolSize(2);
            dataSource.setInitialPoolSize(10);
            dataSource.setMaxStatements(100);
        } catch (PropertyVetoException | IOException e) {
            e.printStackTrace(System.err);
        }
    }
    @Nullable
    public static Connection getConnection() throws SQLException {
        // 获得数据库连接
        return dataSource.getConnection();
    }
}
