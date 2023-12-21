package com.system.DAO.utils;

import com.system.DAO.dao.RocketDao;
import com.system.DAO.polo.Rocket;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class RocketDaoImpl extends BasicDaoImpl implements RocketDao {
    private String addSQL = "INSERT INTO `%s` (%s) VALUES (%s)";
    private String updateSQL = "UPDATE `%s` SET %s WHERE %s";
    private String deleteSQL = "DELETE FROM `%s` WHERE %s";
    private String getSQL = "SELECT %s FROM `%s` %s";
    private String sentence = "WHERE %s";
    private final String rocketIdHead;
    private final String rocketNameHead;
    private final String rocketLaunchDateHead;
    private final String rocketCarryPeopleHead;
    private final String rocketInOrbitTimeHead;

    public RocketDaoImpl(String tableName, String rocketIdHead, String rocketNameHead, String rocketLaunchDateHead, String rocketCarryPeopleHead, String rocketInOrbitTimeHead) {
        super();
        String format = String.format("`%s`, `%s`, `%s`, `%s` `%s`", rocketIdHead, rocketNameHead, rocketLaunchDateHead, rocketCarryPeopleHead, rocketInOrbitTimeHead);
        this.rocketIdHead = rocketIdHead;
        this.rocketNameHead = rocketNameHead;
        this.rocketLaunchDateHead = rocketLaunchDateHead;
        this.rocketCarryPeopleHead = rocketCarryPeopleHead;
        this.rocketInOrbitTimeHead = rocketInOrbitTimeHead;
        addSQL = String.format(addSQL,
                tableName,
                format,
                "%s");
        updateSQL = String.format(updateSQL,
                tableName,
                String.format("`%s` = ?, `%s` = ?, `%s` = ?, `%s` = ?",
                        this.rocketNameHead,
                        this.rocketLaunchDateHead,
                        this.rocketCarryPeopleHead,
                        this.rocketInOrbitTimeHead),
                String.format("`%s` = ?", this.rocketIdHead));
        deleteSQL = String.format(deleteSQL,
                tableName,
                String.format("`%s` = ?", this.rocketIdHead));
        getSQL = String.format(getSQL,
                format,
                tableName,
                "%s");
        sentence = String.format(sentence, String.format("`%s` = ?", this.rocketIdHead));
    }

    @Override
    public boolean addRocket(Rocket rocket) throws Exception {
        return super.add(addSQL,
                rocket.getRocketID(),
                rocket.getRocketName(),
                rocket.getLaunchDate(),
                rocket.getCarryPeople(),
                rocket.getInOrbitTime());
    }

    @Override
    public boolean updateRocket(Rocket rocket) throws Exception {
        return super.update(updateSQL, rocket.getRocketName(), rocket.getLaunchDate(), rocket.getCarryPeople(), rocket.getInOrbitTime(), rocket.getRocketID());
    }

    @Override
    public boolean deleteRocket(int id) throws Exception {
        return super.delete(deleteSQL, id);
    }

    @Override
    public Rocket getRocketByName(String name) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ResultSet rst = super.get(String.format(getSQL, sentence), name);
        if (rst.next()) {
            return new Rocket(rst.getInt(rocketIdHead), rst.getString(rocketNameHead), LocalDateTime.parse(rst.getString(rocketLaunchDateHead), formatter), rst.getString(rocketCarryPeopleHead), rst.getInt(rocketInOrbitTimeHead));
        }
        return null;
    }

    @Override
    public List<Rocket> getAllRockets() throws Exception {
        return null;
    }
}
