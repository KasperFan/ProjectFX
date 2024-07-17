package com.system.DAO;

import java.util.List;

public interface BasicDAO<T> {
    boolean add(T t) throws Exception;

    boolean update(T t) throws Exception;

    boolean delete(int id) throws Exception;

    T get(int id) throws Exception;

    List<T> getAll() throws Exception;
}
