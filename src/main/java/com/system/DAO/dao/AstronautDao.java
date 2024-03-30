package com.system.DAO.dao;

import com.system.DAO.entity.Astronaut;

import java.util.List;

public interface AstronautDao extends BasicDAO<Astronaut> {
    @Override
    boolean add(Astronaut astronaut) throws Exception;

    @Override
    boolean update(Astronaut astronaut) throws Exception;

    @Override
    boolean delete(int id) throws Exception;

    @Override
    Astronaut get(int id) throws Exception;

    @Override
    List<Astronaut> getAll() throws Exception;

    List<Astronaut> getAll(int id) throws Exception;

    List<Astronaut> getAll(String name) throws Exception;
}
