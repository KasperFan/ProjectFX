package com.system.DAO.Impl;

import com.system.DAO.RocketDao;
import com.system.DAO.entity.Rocket;
import com.system.utils.DBUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RocketDaoImpl extends DBUtil implements RocketDao {
    private String addSQL = "INSERT INTO `%s` (%s) VALUES (%s);";
    private String updateSQL = "UPDATE `%s` SET %s WHERE %s";
    private String deleteSQL = "DELETE FROM `%s` WHERE %s";
    private String getSQL = "SELECT %s FROM `%s` %s;";
    private String sentence = "WHERE %s";

    public RocketDaoImpl() {
        super();
        String format = String.format("`%s`, `%s`, `%s`, `%s`", "rid", "r_name", "launch_date", "in_orbitTime");
        addSQL = String.format(addSQL,
                "rocket",
                String.format("`%s`, `%s`, `%s`", "r_name", "launch_date", "in_orbitTime"),
                "?, ?, ?");
        updateSQL = String.format(updateSQL,
                "rocket",
                String.format("`%s` = ?, `%s` = ?, `%s` = ?",
                        "r_name",
                        "launch_date",
                        "in_orbitTime"),
                String.format("`%s` = ?", "rid"));
        deleteSQL = String.format(deleteSQL,
                "rocket",
                String.format("`%s` = ?", "rid"));
        getSQL = String.format(getSQL,
                format,
                "rocket",
                "%s");
        sentence = String.format(sentence, String.format("`%s` = ?", "rid"));
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
        List<Rocket> rockets = new ArrayList<>();
        ResultSet rst = super.executeQuery(String.format(getSQL, ""));
        while (rst.next()) {
            rockets.add(initRocket(rst));
        }
        return rockets;
    }

    @NotNull
    @Contract("_ -> new")
    private Rocket initRocket(@NotNull ResultSet rst) throws Exception {
        var launch_date = rst.getString("launch_date");
        var inOrbitTime = rst.getInt("in_orbitTime");
        if (launch_date != null && !Objects.equals(launch_date, "") && !Objects.equals(launch_date, "null")) {
            if (inOrbitTime == 0) {
                inOrbitTime = -1;
            }
        } else {
            launch_date = "未发射";
            inOrbitTime = -1;
        }
        return new Rocket(rst.getInt("rid"), rst.getString("r_name"), launch_date, inOrbitTime);
    }

    @Override
    public List<Rocket> getAll(int id) throws Exception {
        List<Rocket> rockets = new ArrayList<>();
        ResultSet rst = super.executeQuery(String.format(getSQL, "WHERE `rid` = ?"), id);
        while (rst.next()) {
            rockets.add(initRocket(rst));
        }
        return rockets;
    }

    @Override
    public List<Rocket> getAll(String name) throws Exception{
        List<Rocket> rockets = new ArrayList<>();
        ResultSet rst = super.executeQuery(String.format(getSQL, "WHERE `r_name` LIKE ?"), String.format("%%%s%%", name));
        while (rst.next()) {
            rockets.add(initRocket(rst));
        }
        return rockets;
    }
}
