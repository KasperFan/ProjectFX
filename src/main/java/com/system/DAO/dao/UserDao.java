package com.system.DAO.dao;

import com.system.DAO.polo.User;

import java.util.List;

public interface UserDao extends BasicDAO {
    boolean addUser(User user) throws Exception;

    boolean updateUser(User user) throws Exception;

    boolean deleteUser(int id) throws Exception;

    User getUserById(int id) throws Exception;

    List<User> getAllUser() throws Exception;
}
