package com.system.DAO;

import com.system.DAO.entity.Rocket;

import java.util.List;

public interface RocketDao extends BasicDAO<Rocket> {
    boolean addRocket(Rocket rocket) throws Exception;

    boolean updateRocket(Rocket rocket) throws Exception;

    boolean deleteRocket(int id) throws Exception;

    Rocket getRocketByName(String name) throws Exception;

    List<Rocket> getAllRockets() throws Exception;
}