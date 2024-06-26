package com.system.DAO.dao;

import com.system.DAO.entity.User;

import java.util.List;

public interface UserDao extends BasicDAO<User> {
    @Override
    boolean add(User user) throws Exception;

    @Override
    boolean update(User user) throws Exception;

    @Override
    boolean delete(int id) throws Exception;

    @Override
    User get(int id) throws Exception;

    @Override
    List<User> getAll() throws Exception;

    List<User> getAll(int id) throws Exception;

    List<User> getAll(String name) throws Exception;
}
