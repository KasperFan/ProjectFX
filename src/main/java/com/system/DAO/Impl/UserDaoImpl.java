package com.system.DAO.Impl;

import com.system.DAO.dao.UserDao;
import com.system.DAO.entity.User;
import com.system.utils.DBUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends DBUtil implements UserDao {
    private String addSQL = "INSERT INTO `%s` (%s) VALUES (%s)";
    private String updateSQL = "UPDATE `%s` SET %s WHERE %s";
    private String deleteSQL = "DELETE FROM `%s` WHERE %s";
    private String getSQL = "SELECT %s FROM `%s` %s";
    private String sentence = "WHERE %s";
    private final String userIdHead;
    private final String userNameHead;
    private final String userPasswordHead;
    private final String userAdminHead;

    public UserDaoImpl(String tableName, String userIdHead, String userNameHead, String userPasswordHead, String userAdminHead) {
        super();
        String format = String.format("`%s`, `%s`, `%s`, `%s`", userIdHead, userNameHead, userPasswordHead, userAdminHead);
        this.userIdHead = userIdHead;
        this.userNameHead = userNameHead;
        this.userPasswordHead = userPasswordHead;
        this.userAdminHead = userAdminHead;
        addSQL = String.format(addSQL,
                tableName,
                String.format("`%s`, `%s`, `%s`", userNameHead, userPasswordHead, userAdminHead),
                "?, ?, ?");
        updateSQL = String.format(updateSQL,
                tableName,
                String.format("`%s` = ?, `%s` = ?", this.userNameHead, this.userPasswordHead),
                String.format("`%s` = ?", this.userIdHead));
        deleteSQL = String.format(deleteSQL,
                tableName,
                "`%s` = ?");
        getSQL = String.format(getSQL,
                format,
                tableName,
                "%s");
        sentence = String.format(sentence, String.format("`%s` = ?", this.userNameHead));
    }


    @Override
    public boolean add(@NotNull User user) throws Exception {
        return super.executeUpdate(addSQL, user.getName(), user.getPassword(), user.isAdmin() ? 1 : 0) > 0;
    }

    @Override
    public boolean update(@NotNull User user) throws Exception {
        return super.executeUpdate(updateSQL, user.getName(), user.getPassword(), user.getId()) > 0;
    }

    @Override
    public boolean delete(int id) throws Exception {
        return super.executeUpdate(String.format(deleteSQL, userIdHead), id) > 0;
    }

    @Nullable
    @Override
    public User get(int id) throws Exception {
        ResultSet rst = super.executeQuery(String.format(getSQL, String.format(sentence, "`uid` = ?")), id);
        if (rst.next()) {
            return new User(rst.getInt(userIdHead), rst.getString(userNameHead), rst.getString(userPasswordHead), rst.getInt(userAdminHead) == 1);
        }
        return null;
    }

    @Nullable
    public User getUserByName(String name) throws Exception {
        ResultSet rst = super.executeQuery(String.format(getSQL, sentence), name);
        if (rst.next()) {
            return new User(rst.getInt(userIdHead), rst.getString(userNameHead), rst.getString(userPasswordHead), rst.getInt(userAdminHead) == 1);
        }
        return null;
    }

    @Override
    public List<User> getAll() throws Exception {
        List<User> list = new ArrayList<>();
        ResultSet rst = super.executeQuery(String.format(getSQL, "LIMIT 0,?"), 1000);
        while (rst.next()) {
            list.add(new User(rst.getInt(userIdHead), rst.getString(userNameHead), rst.getString(userPasswordHead), rst.getInt(userAdminHead) == 1));
        }
        return list;
    }

    public List<User> getAll(int id) throws Exception {
        List<User> list = new ArrayList<>();
        ResultSet rst = super.executeQuery(String.format(getSQL, String.format(sentence, "`uid` = ?")), id);
        while (rst.next()) {
            list.add(new User(rst.getInt(userIdHead), rst.getString(userNameHead), rst.getString(userPasswordHead), rst.getInt(userAdminHead) == 1));
        }
        return list;
    }

    public List<User> getAll(String name) throws Exception {
        List<User> list = new ArrayList<>();
        ResultSet rst = super.executeQuery(String.format(getSQL, String.format(sentence, "`userName` LIKE ?")), String.format("%%%s%%", name));
        while (rst.next()) {
            list.add(new User(rst.getInt(userIdHead), rst.getString(userNameHead), rst.getString(userPasswordHead), rst.getInt(userAdminHead) == 1));
        }
        return list;
    }
}
