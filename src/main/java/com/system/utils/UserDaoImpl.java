package com.system.utils;

import com.system.base.User;
import com.system.dao.UserDao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends DBUtil implements UserDao, AutoCloseable {
    public UserDaoImpl(String dbName, String user, String password, String tableName, String userIdHead,
                       String userNameHead, String userPasswordHead) {
        this("localhost", 3306, dbName, user, password, tableName, userIdHead,
                userNameHead, userPasswordHead);
    }

    public UserDaoImpl(String hostName, int port, String dbName, String user, String password, String tableName,
                       String userIdHead, String userNameHead, String userPasswordHead) {
        super(hostName, port, dbName, user, password);
        addSQL = String.format(addSQL,
                tableName,
                String.format("`%s`, `%s`, `%s`",
                        userIdHead,
                        userNameHead,
                        userPasswordHead),
                "? ? ?");
        updateSQL = String.format(updateSQL,
                tableName,
                String.format("`%s` = ?, `%s` = ?", userNameHead, userPasswordHead),
                String.format("`%s` = ?", userIdHead));
        deleteSQL = String.format(deleteSQL,
                tableName,
                String.format("`%s` = ?", userIdHead));
        getSQL = String.format(getSQL,
                String.format("`%s`, `%s`, `%s`", userIdHead, userNameHead, userPasswordHead),
                tableName,
                "%s");
        sentence = String.format(sentence, String.format("`%s` = ?", userIdHead));
    }


    @Override
    public boolean addUser(User user) throws Exception {
        return super.executeUpdate(addSQL, user.getId(), user.getName(), user.getPassword()) > 0;
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        return super.executeUpdate(updateSQL, user.getName(), user.getPassword(), user.getId()) > 0;
    }

    @Override
    public boolean deleteUser(int id) throws Exception {
        return super.executeUpdate(deleteSQL, id) > 0;
    }

    @Override
    public User getUserById(int id) throws Exception {
        ResultSet rst = super.executeQuery(String.format(getSQL, sentence), id);
        if (rst.next()) {
            return new User(rst.getInt(1), rst.getString(2), rst.getString(3));
        }
        return null;
    }

    @Override
    public List<User> getAllUser() throws Exception {
        List<User> userList = new LinkedList<>();
        ResultSet rst = super.executeQuery(String.format(getSQL, "LIMIT ?"), 100);
        while (rst.next()) {
            userList.add(new User(rst.getInt(1), rst.getString(2), rst.getString(3)));
        }
        return userList;
    }

    @Override
    public void close() {
        super.close();
    }
}
