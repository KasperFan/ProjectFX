package com.system.DAO.Impl;

import com.system.DAO.BasicDAO;
import com.system.utils.DBUtil;

import java.sql.ResultSet;

public class BasicDaoImpl<T> extends DBUtil implements BasicDAO<T>, AutoCloseable {
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
