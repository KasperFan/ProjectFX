package com.system.DAO;

import com.system.DAO.entity.Rocket;

import java.util.List;

public interface RocketDao extends BasicDAO<Rocket> {
    @Override
    boolean add(Rocket rocket) throws Exception;

    @Override
    boolean update(Rocket rocket) throws Exception;

    @Override
    boolean delete(int id) throws Exception;

    @Override
    Rocket get(int id) throws Exception;

    Rocket get(String name) throws Exception;

    @Override
    List<Rocket> getAll() throws Exception;

    List<Rocket> getAll(int id) throws Exception;

    List<Rocket> getAll(String name) throws Exception;
}