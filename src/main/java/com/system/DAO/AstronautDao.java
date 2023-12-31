package com.system.DAO;

import com.system.DAO.entity.Astronaut;

import java.util.List;

public interface AstronautDao extends BasicDAO<Astronaut> {
    boolean addAstronaut(Astronaut astronaut) throws Exception;

    boolean deleteAstronaut(int id) throws Exception;

    boolean updateAstronaut(Astronaut astronaut) throws Exception;

    Astronaut getAstronautById(int id) throws Exception;

    List<Astronaut> getAllAstronauts() throws Exception;
}
