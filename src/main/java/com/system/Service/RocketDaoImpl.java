package com.system.Service;

import com.system.DAO.dao.RocketDao;
import com.system.DAO.polo.Rocket;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RocketDaoImpl extends BasicDaoImpl implements RocketDao {
    private String addSQL = "INSERT INTO `%s` (%s) VALUES (%s)";
    private String updateSQL = "UPDATE `%s` SET %s WHERE %s";
    private String deleteSQL = "DELETE FROM `%s` WHERE %s";
    private String getSQL = "SELECT %s FROM `%s` %s";
    private String sentence = "WHERE %s";
    private final String rocketIdHead;
    private final String rocketNameHead;
    private final String rocketLaunchDateHead;
    private final String rocketInOrbitTimeHead;

    public RocketDaoImpl(String tableName, String rocketIdHead, String rocketNameHead, String rocketLaunchDateHead, String rocketInOrbitTimeHead) {
        super();
        String format = String.format("`%s`, `%s`, `%s`, `%s`", rocketIdHead, rocketNameHead, rocketLaunchDateHead, rocketInOrbitTimeHead);
        this.rocketIdHead = rocketIdHead;
        this.rocketNameHead = rocketNameHead;
        this.rocketLaunchDateHead = rocketLaunchDateHead;
        this.rocketInOrbitTimeHead = rocketInOrbitTimeHead;
        addSQL = String.format(addSQL,
                tableName,
                format,
                "%s");
        updateSQL = String.format(updateSQL,
                tableName,
                String.format("`%s` = ?, `%s` = ?, `%s` = ?",
                        this.rocketNameHead,
                        this.rocketLaunchDateHead,
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
        /* SELECT r.rocketID, r.rocketName, r.launchDate, e.anames, r.in_orbitTime FROM `rocket` r LEFT JOIN `event` e ON r.rocketID = e.rocketID */
        ResultSet rst = super.get(String.format(getSQL, sentence), name);
        if (rst.next()) {
            Rocket rocket = new Rocket(rst.getInt(rocketIdHead), rst.getString(rocketNameHead), rst.getString(rocketLaunchDateHead), rst.getInt(rocketInOrbitTimeHead));
            try (BasicDaoImpl basicDao = new BasicDaoImpl()) {
                ResultSet all = basicDao.getAll("SELECT e.anames FROM `rocket` r LEFT JOIN `event` e ON r.rocketID = e.rocketID");
                if (all.next()) {
                    String peoples = all.getString(1);
                    if (!Objects.equals(peoples, "NULL")) {
                        rocket.setCarryPeople(peoples);
                    }else{
                        rocket.setCarryPeople("空");
                    }
                }
            }
            return rocket;
        }
        return null;
    }

    @Override
    public List<Rocket> getAllRockets() throws Exception {
        LinkedList<Rocket> rockets = new LinkedList<>();
        ResultSet rst = super.get(String.format(getSQL, ""));
        if (rst.next()) {
            Rocket rocket = new Rocket(rst.getInt(rocketIdHead), rst.getString(rocketNameHead), rst.getString(rocketLaunchDateHead), rst.getInt(rocketInOrbitTimeHead));
            try (BasicDaoImpl basicDao = new BasicDaoImpl()) {
                ResultSet all = basicDao.getAll("SELECT e.anames FROM `rocket` r LEFT JOIN `event` e ON r.rocketID = e.rocketID");
                if (all.next()) {
                    String peoples = all.getString(1);
                    if (!Objects.equals(peoples, "NULL")) {
                        rocket.setCarryPeople(peoples);
                    }else{
                        rocket.setCarryPeople("空");
                    }
                }
            }
            rockets.add(rocket);
        }
        return rockets;
    }
}
