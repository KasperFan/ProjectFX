package com.system.DAO;

import com.system.DAO.entity.User;

import java.util.List;

public interface UserDao extends BasicDAO<User> {
    boolean addUser(User user) throws Exception;

    boolean updateUser(User user) throws Exception;

    boolean deleteUser(int id) throws Exception;

    User getUserByName(String name) throws Exception;

    List<User> getAllUser() throws Exception;
}
