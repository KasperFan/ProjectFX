package com.system.DAO.utils;

import com.system.DAO.dao.UserDao;
import com.system.DAO.polo.User;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends BasicDaoImpl implements UserDao {
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
    public boolean addUser(User user) throws Exception {
        return super.add(addSQL, user.getName(), user.getPassword(), user.isAdmin() ? 1 : 0);
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        return super.add(updateSQL, user.getName(), user.getPassword(), user.getId());
    }

    @Override
    public boolean deleteUser(int id) throws Exception {
        return super.delete(String.format(deleteSQL, userIdHead), id);
    }


    @Nullable
    @Override
    public User getUserByName(String name) throws Exception {
        ResultSet rst = super.get(String.format(getSQL, sentence), name);
        if (rst.next()) {
            return new User(rst.getInt(userIdHead), rst.getString(userNameHead), rst.getString(userPasswordHead), rst.getInt(userAdminHead) == 1);
        }
        return null;
    }

    @Override
    public List<User> getAllUser() throws Exception {
        List<User> list = new LinkedList<>();
        ResultSet rst = super.get(getSQL);
        while (rst.next()) {
            list.add(new User(rst.getInt(userIdHead), rst.getString(userNameHead), rst.getString(userPasswordHead), rst.getInt(userAdminHead) == 1));
        }
        return list;
    }
}
