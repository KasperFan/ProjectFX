package com.system.DAO.dao;

import com.system.DAO.polo.Rocket;

import java.util.List;

public interface RocketDao extends BasicDAO {
    boolean addRocket(Rocket rocket) throws Exception;

    boolean updateRocket(Rocket rocket) throws Exception;

    boolean deleteRocket(int id) throws Exception;

    Rocket getRocketByName(String  name) throws Exception;

    List<Rocket> getAllRockets() throws Exception;
}