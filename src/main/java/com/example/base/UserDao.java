package com.example.base;

import com.example.base.User;

import java.util.List;

public interface UserDao {
    boolean addUser(User user) throws Exception;

    boolean updateUser(User user) throws Exception;

    boolean deleteUser(int id) throws Exception;

    User getUserById(int id) throws Exception;

    List<User> getAllUser() throws Exception;
}
