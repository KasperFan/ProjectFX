package com.example.utils;

import com.example.base.User;
import com.example.base.UserDao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements UserDao, AutoCloseable {
    private static String adusrsql = "INSERT INTO `user_list` (`id`, `name`, `pswd_sha`) VALUES (?, '?', '?')";
    private static String apdteusrsql = "UPDATE `user_list` SET `pswd_sha` = '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918' WHERE `SID` = 1";
    private static String deusrsql = "DELETE FROM `user_list` where `id` = ?";
    private static String getusrsql = "SELECT `id`, `name`, `pswd_sha` FROM `user_list` ? ?";
    private DBUtil dbutil = null;

    public UserDaoImpl(String dbName, String user, String password) {
        this("localhost", 3306, dbName, user, password);
    }

    public UserDaoImpl(String hostName, int port, String dbName, String user, String password) {
        dbutil = DBUtil.dbUtil(hostName, port, dbName, user, password);
    }

    @Override
    public boolean addUser(User user) throws Exception {
        return dbutil.executeUpdate(adusrsql, String.valueOf(user.getId()), user.getName(), user.getPassword()) > 0;
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        return dbutil.executeUpdate(apdteusrsql, String.valueOf(user.getId()), user.getName(), user.getPassword()) > 0;
    }

    @Override
    public boolean deleteUser(int id) throws Exception {
        return dbutil.executeUpdate(deusrsql, String.valueOf(id)) > 0;
    }

    @Override
    public User getUserById(int id) throws Exception {
        ResultSet rst = dbutil.executeQuery(getusrsql, "WHERE", "`id` = "+ id);
        if (rst.next()) {
            return new User(rst.getInt(1), rst.getString(2), rst.getString(3));
        }
        return null;
    }

    @Override
    public List<User> getAllUser() throws Exception {
        List<User> userList = new LinkedList<>();
        ResultSet rst = dbutil.executeQuery(getusrsql, "");
        while (rst.next()) {
             userList.add(new User(rst.getInt(1), rst.getString(2), rst.getString(3)));
        }
        return userList;
    }

    @Override
    public void close() throws Exception {
        dbutil.close();
    }
}
