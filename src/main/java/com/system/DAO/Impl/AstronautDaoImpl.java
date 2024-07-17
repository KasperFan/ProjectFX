package com.system.DAO.Impl;

import com.system.DAO.dao.AstronautDao;
import com.system.DAO.entity.Astronaut;
import com.system.utils.DBUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AstronautDaoImpl extends DBUtil implements AstronautDao {
    private final String selectSQL = "SELECT * FROM `astronaut` %s";

    @Override
    public boolean add(@NotNull Astronaut astronaut) throws Exception {
        String addSQL = "INSERT INTO `astronaut` (`aid`, `name`, `age`, `sex`) VALUES (?, ?, ?, ?)";
        return super.executeUpdate(addSQL, astronaut.getId(), astronaut.getName(), astronaut.getAge(), astronaut.getSex()) > 0;
    }

    @Override
    public boolean delete(int id) throws Exception {
        String deleteSQL = "DELETE FROM `astronaut` WHERE `aid` = ?";
        return super.executeUpdate(deleteSQL, id) > 0;
    }

    @Override
    public boolean update(@NotNull Astronaut astronaut) throws Exception {
        String updateSQL = "UPDATE `astronaut` SET `name` = ?, `age` = ?, `sex` = ? WHERE `aid` = ?";
        return super.executeUpdate(updateSQL, astronaut.getName(), astronaut.getAge(), astronaut.getSex(), astronaut.getId()) > 0;
    }

    @Nullable
    @Override
    public Astronaut get(int id) throws Exception {
        String sentence = "WHERE `aid` = ?";
        ResultSet resultSet = super.executeQuery(String.format(selectSQL, sentence), id);
        if (resultSet.next()) {
            return new Astronaut(resultSet.getInt("aid"), resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("sex"));
        }
        return null;
    }

    @Override
    public List<Astronaut> getAll() throws Exception {
        List<Astronaut> astronauts = new ArrayList<>();
        ResultSet resultSet = super.executeQuery(String.format(selectSQL, "LIMIT 0,?"), 1000);
        while (resultSet.next()) {
            astronauts.add(new Astronaut(resultSet.getInt("aid"), resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("sex")));
        }
        return astronauts;
    }

    @Override
    public List<Astronaut> getAll(int id) throws Exception {
        List<Astronaut> astronauts = new ArrayList<>();
        ResultSet resultSet = super.executeQuery(String.format(selectSQL, "WHERE `aid` = ?"), id);
        while (resultSet.next()) {
            astronauts.add(new Astronaut(resultSet.getInt("aid"), resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("sex")));
        }
        return astronauts;
    }

    @Override
    public List<Astronaut> getAll(String name) throws Exception {
        List<Astronaut> astronauts = new ArrayList<>();
        ResultSet resultSet = super.executeQuery(String.format(selectSQL, "WHERE `aid` LIKE ?"), String.format("%%%s%%", name));
        while (resultSet.next()) {
            astronauts.add(new Astronaut(resultSet.getInt("aid"), resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("sex")));
        }
        return astronauts;
    }
}
