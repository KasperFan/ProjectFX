package com.system.DAO;

import java.sql.ResultSet;

public interface BasicDAO<T> {
    boolean add(String preparedSQL, Object... params) throws Exception;

    boolean update(String preparedSQL, Object... params) throws Exception;

    boolean delete(String preparedSQL, Object... params) throws Exception;

    ResultSet get(String preparedSQL, Object... params) throws Exception;

    ResultSet getAll(String preparedSQL) throws Exception;
}
