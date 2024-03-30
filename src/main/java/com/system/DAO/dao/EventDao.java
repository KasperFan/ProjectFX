package com.system.DAO.dao;

import com.system.DAO.entity.Event;

import java.util.List;

public interface EventDao extends BasicDAO<Event> {
    @Override
    boolean add(Event event) throws Exception;

    @Override
    boolean update(Event event) throws Exception;

    @Override
    boolean delete(int id) throws Exception;

    @Override
    Event get(int id) throws Exception;

    Event get(String name) throws Exception;

    @Override
    List<Event> getAll() throws Exception;

    List<Event> getAll(int id) throws Exception;

    List<Event> getAll(String name) throws Exception;
}
