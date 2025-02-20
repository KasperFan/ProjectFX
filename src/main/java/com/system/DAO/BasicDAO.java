package com.system.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BasicDAO<T> {
    Map<String, String> baseSqlMap = new HashMap<>() {{
        put("addSql", "INSERT INTO `%s` (%s) VALUES (%s);");
        put("deleteSql", "DELETE FROM `%s` WHERE `%s` = ?;");
        put("updateSql", "UPDATE `%s` SET %s WHERE `%s` = ?");
        put("getSql", "SELECT %s FROM `%s` %s;");
    }};

    boolean add(T t) throws Exception;

    boolean update(T t) throws Exception;

    boolean delete(int id) throws Exception;

    T get(int id) throws Exception;

    List<T> getAll() throws Exception;
}
