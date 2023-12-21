package com.system.DAO.utils;

import com.system.DAO.dao.BasicDAO;
import com.system.DAO.polo.User;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class BasicDaoImpl extends DBUtil implements BasicDAO, AutoCloseable {
    public BasicDaoImpl() {
        super();
    }

    @Override
    public boolean add(String preparedSQL, Object... params) throws Exception {
        return super.executeUpdate(preparedSQL, params) > 0;
    }

    @Override
    public boolean update(String preparedSQL, Object... params) throws Exception {
        return super.executeUpdate(preparedSQL, params) > 0;
    }

    @Override
    public boolean delete(String preparedSQL, Object... params) throws Exception {
        return super.executeUpdate(preparedSQL, params) > 0;
    }

    @Override
    public ResultSet get(String preparedSQL, Object... params) throws Exception {
        return super.executeQuery(preparedSQL, params);
    }

    @Override
    public ResultSet getAll(String preparedSQL) throws Exception {
        return super.executeQuery(preparedSQL);
    }

    @Override
    public void close() {
        super.close();
    }
}
