package com.system.DAO.Impl;

import com.system.DAO.AstronautDao;
import com.system.DAO.entity.Astronaut;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class AstronautDaoImpl extends BasicDaoImpl<Astronaut> implements AstronautDao {
    private final String deleteSQL = "DELETE FROM `astronaut` WHERE `aid` = ?";
    private final String updateSQL = "UPDATE `astronaut` SET `name` = ?, `age` = ?, `sex` = ? WHERE `aid` = ?";
    private final String selectSQL = "SELECT * FROM `astronaut` %s";
    private final String sentence = "WHERE `aid` = ?";

    @Override
    public boolean addAstronaut(@NotNull Astronaut astronaut) throws Exception {
        String addSQL = "INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (?, ?, ?, ?)";
        return add(addSQL, astronaut.getId(), astronaut.getName(), astronaut.getAge(), astronaut.getSex());
    }

    @Override
    public boolean deleteAstronaut(int id) throws Exception {
        return delete(deleteSQL, id);
    }

    @Override
    public boolean updateAstronaut(@NotNull Astronaut astronaut) throws Exception {
        return update(updateSQL, astronaut.getName(), astronaut.getAge(), astronaut.getSex(), astronaut.getId());
    }

    @Nullable
    @Override
    public Astronaut getAstronautById(int id) throws Exception {
        ResultSet resultSet = get(String.format(selectSQL, sentence), id);
        if (resultSet.next()) {
            return new Astronaut(resultSet.getInt("aid"), resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("sex"));
        }
        return null;
    }

    @Override
    public List<Astronaut> getAllAstronauts() throws Exception {
        List<Astronaut> astronauts = new LinkedList<>();
        ResultSet resultSet = get(String.format(selectSQL, "LIMIT 0,?"), 1000);
        while (resultSet.next()) {
            astronauts.add(new Astronaut(resultSet.getInt("aid"), resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("sex")));
        }
        return astronauts;
    }

    public List<Astronaut> getAllAstronautsById(int id) throws Exception {
        LinkedList<Astronaut> astronauts = new LinkedList<>();
        ResultSet resultSet = get(String.format(selectSQL, "WHERE `aid` = ?"), id);
        while (resultSet.next()) {
            astronauts.add(new Astronaut(resultSet.getInt("aid"), resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("sex")));
        }
        return astronauts;
    }

    public List<Astronaut> getAllAstronautsByName(String name) throws Exception {
        LinkedList<Astronaut> astronauts = new LinkedList<>();
        ResultSet resultSet = get(String.format(selectSQL, "WHERE `aid` LIKE ?"), String.format("%%%s%%", name));
        while (resultSet.next()) {
            astronauts.add(new Astronaut(resultSet.getInt("aid"), resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("sex")));
        }
        return astronauts;
    }
}
