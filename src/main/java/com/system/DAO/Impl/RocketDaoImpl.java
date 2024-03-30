package com.system.DAO.Impl;

import com.system.DAO.dao.RocketDao;
import com.system.DAO.entity.Rocket;
import com.system.utils.DBUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RocketDaoImpl extends DBUtil implements RocketDao {
    private String addSQL = "INSERT INTO `%s` (%s) VALUES (%s)";
    private String updateSQL = "UPDATE `%s` SET %s WHERE %s";
    private String deleteSQL = "DELETE FROM `%s` WHERE %s";
    private String getSQL = "SELECT %s FROM `%s` %s";
    private String sentence = "WHERE %s";

    public RocketDaoImpl(String tableName, String rocketIdHead, String rocketNameHead, String rocketLaunchDateHead, String rocketInOrbitTimeHead) {
        super();
        String format = String.format("`%s`, `%s`, `%s`, `%s`", rocketIdHead, rocketNameHead, rocketLaunchDateHead, rocketInOrbitTimeHead);
        addSQL = String.format(addSQL,
                tableName,
                String.format("`%s`, `%s`, `%s`", rocketNameHead, rocketLaunchDateHead, rocketInOrbitTimeHead),
                "?, ?, ?");
        updateSQL = String.format(updateSQL,
                tableName,
                String.format("`%s` = ?, `%s` = ?, `%s` = ?",
                        rocketNameHead,
                        rocketLaunchDateHead,
                        rocketInOrbitTimeHead),
                String.format("`%s` = ?", rocketIdHead));
        deleteSQL = String.format(deleteSQL,
                tableName,
                String.format("`%s` = ?", rocketIdHead));
        getSQL = String.format(getSQL,
                format,
                tableName,
                "%s");
        sentence = String.format(sentence, String.format("`%s` = ?", rocketIdHead));
    }

    @Override
    public boolean add(@NotNull Rocket rocket) throws Exception {
        return super.executeUpdate(addSQL, rocket.getRocketName(), rocket.getLaunchDate(), rocket.getInOrbitTime()) > 0;
    }

    @Override
    public boolean update(@NotNull Rocket rocket) throws Exception {
        return super.executeUpdate(updateSQL, rocket.getRocketName(), rocket.getLaunchDate(), rocket.getInOrbitTime(), rocket.getRocketID()) > 0;
    }

    @Override
    public boolean delete(int id) throws Exception {
        return super.executeUpdate(deleteSQL, id) > 0;
    }

    @Override
    public Rocket get(int id) throws Exception {
        ResultSet rst = super.executeQuery(String.format(getSQL, String.format(sentence, "`rid` = ?")), id);
        if (rst.next()) {
            return initRocket(rst);
        }
        return null;
    }

    @Override
    public Rocket get(String name) throws Exception {
        ResultSet rst = super.executeQuery(String.format(getSQL, sentence), name);
        if (rst.next()) {
            return initRocket(rst);
        }
        return null;
    }

    @Override
    public List<Rocket> getAll() throws Exception {
        LinkedList<Rocket> rockets = new LinkedList<>();
        ResultSet rst = super.executeQuery(String.format(getSQL, ""));
        while (rst.next()) {
            rockets.add(initRocket(rst));
        }
        return rockets;
    }

    @NotNull
    @Contract("_ -> new")
    private Rocket initRocket(@NotNull ResultSet rst) throws Exception {
        var launchDate = rst.getString("launchDate");
        var inOrbitTime = rst.getInt("in_orbitTime");
        if (launchDate != null && !Objects.equals(launchDate, "") && !Objects.equals(launchDate, "null")) {
            if (inOrbitTime == 0) {
                inOrbitTime = -1;
            }
        } else {
            launchDate = "未发射";
            inOrbitTime = -1;
        }
        return new Rocket(rst.getInt("rocketID"), rst.getString("rocketName"), launchDate, inOrbitTime);
    }

    @Override
    public List<Rocket> getAll(int id) throws Exception {
        LinkedList<Rocket> rockets = new LinkedList<>();
        ResultSet rst = super.executeQuery(String.format(getSQL, "WHERE `rocketID` = ?"), id);
        while (rst.next()) {
            rockets.add(initRocket(rst));
        }
        return rockets;
    }

    @Override
    public List<Rocket> getAll(String name) throws Exception{
        LinkedList<Rocket> rockets = new LinkedList<>();
        ResultSet rst = super.executeQuery(String.format(getSQL, "WHERE `rocketName` LIKE ?"), String.format("%%%s%%", name));
        while (rst.next()) {
            rockets.add(initRocket(rst));
        }
        return rockets;
    }
}
