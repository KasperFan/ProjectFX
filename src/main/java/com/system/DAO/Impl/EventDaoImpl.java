package com.system.DAO.Impl;

import com.system.DAO.dao.EventDao;
import com.system.DAO.entity.Event;
import com.system.utils.DBUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EventDaoImpl extends DBUtil implements EventDao {
    private final String getSQL = "SELECT * FROM `event` %s";

    @Override
    public boolean add(@NotNull Event event) throws Exception {
        try {
            String addSQL = "INSERT INTO `event` (`title`, `time`, `mean`) VALUES (?, ?, ?)";
            boolean result = super.executeUpdate(addSQL, event.getTitle(), event.getTime(), event.getMean()) > 0;
            try (ResultSet resultSet = super.executeQuery("SELECT `eid` FROM `event` WHERE `title` = ?", event.getTitle())) {
                resultSet.next();
                event.setId(resultSet.getInt("eid"));
            }
            updateRocket_alias(event);
            updateAstronaut_alias(event);
            return result;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean update(@NotNull Event event) throws Exception {
        ResultSet resultSet = super.executeQuery("SELECT `eid` FROM `event` WHERE `title` = ?", event.getTitle());
        resultSet.next();
        event.setId(resultSet.getInt("eid"));
        String updateSQL = "UPDATE `event` SET `title` = ?, `time` = ?, `mean` = ? WHERE `eid` = ?";
        boolean result = super.executeUpdate(updateSQL, event.getTitle(), event.getTime(), event.getMean(), event.getId()) > 0;
        if (result) {
            // 删除ev_roc表上的相关记录
            super.executeUpdate("DELETE FROM `ev_roc` WHERE `eid` = ?", event.getId());
            updateRocket_alias(event);
            // 删除ev_ast表上的相关记录
            super.executeUpdate("DELETE FROM `ev_ast` WHERE `eid` = ?", event.getId());
            updateAstronaut_alias(event);
        }
        return result;
    }

    private void updateAstronaut_alias(@NotNull Event event) throws Exception {
        for (var i :
                event.getAstronauts().split(" ")) {
            try {
                super.executeUpdate("INSERT INTO `astronaut` (`name`) VALUES (?)", i);
            } catch (SQLException ignored) {
            } finally {
                var resultSet = super.executeQuery("SELECT `aid` FROM `astronaut` WHERE `name` = ?", i);
                if (resultSet.next()) {
                    super.executeUpdate("INSERT INTO `ev_ast` (`eid`, `aid`) VALUES (?, ?)", event.getId(), resultSet.getInt("aid"));
                }
            }
        }
    }

    private void updateRocket_alias(@NotNull Event event) throws Exception {
        for (var i :
                event.getRocketName().split(" ")) {
            try {
                super.executeUpdate("INSERT INTO `rocket` (`rocketName`) VALUES (?)", i);
            } catch (SQLException ignored) {
            } finally {
                var resultSet = super.executeQuery("SELECT `rocketID` FROM `rocket` WHERE `rocketName` = ?", i);
                if (resultSet.next()) {
                    super.executeUpdate("INSERT INTO `ev_roc` (`eid`, `rid`) VALUES (?, ?)", event.getId(), resultSet.getInt("rocketID"));
                }
            }
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String deleteSQL = "DELETE FROM `event` WHERE `eid` = ?";
        if (super.executeUpdate(deleteSQL, id) > 0) {
            super.executeUpdate("DELETE FROM `ev_roc` WHERE `eid` = ?", id);
            super.executeUpdate("DELETE FROM `ev_ast` WHERE `eid` = ?", id);
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Event get(int id) throws Exception {
        String sentence = "WHERE `eid` = ?";
        ResultSet resultSet = super.executeQuery(String.format(getSQL, sentence), id);
        if (resultSet.next()) {
            return initEvent(resultSet);
        }
        return null;
    }

    @Nullable
    @Override
    public Event get(String name) throws Exception {
        String sentence = "WHERE `title` = ?";
        ResultSet resultSet = super.executeQuery(String.format(getSQL, sentence), name);
        if (resultSet.next()) {
            return initEvent(resultSet);
        }
        return null;
    }

    @Override
    public List<Event> getAll() throws Exception {
        LinkedList<Event> events = new LinkedList<>();
        ResultSet resultSet = super.executeQuery(String.format(getSQL, ""));
        while (resultSet.next()) {
            events.add(initEvent(resultSet));
        }
        return events;
    }

    @NotNull
    private Event initEvent(@NotNull ResultSet resultSet) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<Integer> list = new ArrayList<>();
        Event event = new Event(resultSet.getInt("eid"), resultSet.getString("title"), resultSet.getString("time"), resultSet.getString("mean"));
        ResultSet set = super.executeQuery("SELECT `rid` FROM `ev_roc` WHERE `eid` = ?", event.getId());
        while (set.next()) {
            list.add(set.getInt("rid"));
        }
        for (var i : list) {
            set = super.executeQuery("SELECT `rocketName` FROM `rocket` WHERE `rocketID` = ?", i);
            set.next();
            stringBuffer.append(set.getString("rocketName")).append(" ");
        }
        event.setRocketName(stringBuffer.toString().strip());
        stringBuffer.delete(0, stringBuffer.length());
        list.clear();
        set = super.executeQuery("SELECT `aid` FROM `ev_ast` WHERE `eid` = ?", event.getId());
        while (set.next()) {
            list.add(set.getInt("aid"));
        }
        for (var i : list) {
            set = super.executeQuery("SELECT `name` FROM `astronaut` WHERE `aid` = ?", i);
            set.next();
            stringBuffer.append(set.getString("name")).append(" ");
        }
        event.setAstronauts(stringBuffer.toString().strip());
        return event;
    }

    public List<Event> getAll(int id) throws Exception {
        LinkedList<Event> events = new LinkedList<>();
        ResultSet resultSet = super.executeQuery(String.format(getSQL, "WHERE `eid` = ?"), id);
        while (resultSet.next()) {
            events.add(initEvent(resultSet));
        }
        return events;
    }

    public List<Event> getAll(String name) throws Exception {
        LinkedList<Event> events = new LinkedList<>();
        ResultSet resultSet = super.executeQuery(String.format(getSQL, "WHERE `title` LIKE ?"), String.format("%%%s%%", name));
        while (resultSet.next()) {
            events.add(initEvent(resultSet));
        }
        return events;
    }
}
