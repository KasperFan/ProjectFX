package com.system.DAO.Impl;

import com.system.DAO.EventDao;
import com.system.DAO.entity.Event;
import com.system.utils.DBUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.system.JavaFX.view.ProjectApplication.configData;

public class EventDaoImpl extends DBUtil implements EventDao {
    private final String addSQL, deleteSQL, updateSQL, getSQL, delETRSql, delETASql;
    private static final String tableName, idHead, titleHead, timeHead, meaningHead, ETRTable, ETATable,
            astTableName, astIdHead, astNameHead, rocTableName, rocIdHead, rocNameHead;

    static {
        @SuppressWarnings("unchecked")
        Map<String, Object> tableConfig = (Map<String, Object>) configData.get("eventTable");
        @SuppressWarnings("unchecked")
        Map<String, Object> astTableConfig = (Map<String, Object>) configData.get("astronautTable");
        @SuppressWarnings("unchecked")
        Map<String, Object> rocTableConfig = (Map<String, Object>) configData.get("rocketTable");
        idHead = (String) tableConfig.get("idCol");
        tableName = (String) tableConfig.get("tableName");
        titleHead = (String) tableConfig.get("titleCol");
        timeHead = (String) tableConfig.get("timeCol");
        meaningHead = (String) tableConfig.get("meaningCol");
        ETRTable = (String) tableConfig.get("eventToRocketTable");
        ETATable = (String) tableConfig.get("eventToAstronaut");
        astTableName = (String) astTableConfig.get("tableName");
        astIdHead = (String) astTableConfig.get("idCol");
        astNameHead = (String) astTableConfig.get("nameCol");
        rocTableName = (String) rocTableConfig.get("tableName");
        rocIdHead = (String) rocTableConfig.get("idCol");
        rocNameHead = (String) rocTableConfig.get("nameCol");
    }

    public EventDaoImpl() {
        addSQL = baseSqlMap.get("addSql").formatted("%s", "`%s`, `%s`, `%s`", "?, ?, ?").formatted(tableName, titleHead, timeHead, meaningHead);
        deleteSQL = baseSqlMap.get("deleteSql").formatted(tableName, idHead);
        updateSQL = baseSqlMap.get("updateSql").formatted("%s", "`%s` = ?, `%s` = ?, `%s` = ?", "%s").formatted(tableName, titleHead, timeHead, meaningHead, idHead);
        getSQL = baseSqlMap.get("getSql").formatted("%s", tableName ,"%s");
        delETRSql = baseSqlMap.get("deleteSql").formatted(ETRTable, idHead);
        delETASql = baseSqlMap.get("deleteSql").formatted(ETATable, idHead);
    }

    @Override
    public boolean add(@NotNull Event event) throws Exception {
        try {
            boolean result = super.executeUpdate(addSQL, event.getTitle(), event.getTime(), event.getMean()) > 0;
            try (ResultSet resultSet = super.executeQuery(getSQL.formatted("`%s`".formatted(idHead), "WHERE `%s` = ?;".formatted(titleHead)), event.getTitle())) {
                resultSet.next();
                event.setId(resultSet.getInt(idHead));
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
        ResultSet resultSet = super.executeQuery(getSQL.formatted("`%s`".formatted(idHead), "WHERE `%s` = ?;".formatted(titleHead)), event.getTitle());
        resultSet.next();
        event.setId(resultSet.getInt(idHead));

        boolean result = super.executeUpdate(updateSQL, event.getTitle(), event.getTime(), event.getMean(), event.getId()) > 0;
        if (result) {
            // 删除ev_roc表上的相关记录
            super.executeUpdate(delETRSql, event.getId());
            updateRocket_alias(event);
            // 删除ev_ast表上的相关记录
            super.executeUpdate(delETASql, event.getId());
            updateAstronaut_alias(event);
        }
        return result;
    }

    private void updateAstronaut_alias(@NotNull Event event) throws Exception {
        for (var i :
                event.getAstronauts().split(" ")) {
            try {
                super.executeUpdate(baseSqlMap.get("addSql").formatted(astTableName, "%s".formatted(titleHead), "?"), i);
            } catch (SQLException ignored) {
            } finally {
                var resultSet = super.executeQuery("SELECT `%s` FROM `%s` WHERE `%s` = ?".formatted(astIdHead, astTableName, titleHead), i);
                if (resultSet.next()) {
                    super.executeUpdate("INSERT INTO `%s` (`%s`, `%s`) VALUES (?, ?)".formatted(ETATable, idHead, astIdHead), event.getId(), resultSet.getInt(astIdHead));
                }
            }
        }
    }

    private void updateRocket_alias(@NotNull Event event) throws Exception {
        for (var i :
                event.getRocketName().split(" ")) {
            try {
                super.executeUpdate("INSERT INTO `%s` (`%s`) VALUES (?)".formatted(rocTableName, rocNameHead), i);
            } catch (SQLException ignored) {
            } finally {
                var resultSet = super.executeQuery("SELECT `%s` FROM `%s` WHERE `%s` = ?".formatted(rocIdHead, rocTableName, rocNameHead), i);
                if (resultSet.next()) {
                    super.executeUpdate("INSERT INTO `%s` (`%s`, `%s`) VALUES (?, ?)".formatted(ETRTable, idHead, rocIdHead), event.getId(), resultSet.getInt(rocIdHead));
                }
            }
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        if (super.executeUpdate(deleteSQL, id) > 0) {
            super.executeUpdate(delETRSql, id);
            super.executeUpdate(delETASql, id);
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Event get(int id) throws Exception {
        String sentence = "WHERE `%s` = ?".formatted(idHead);
        ResultSet resultSet = super.executeQuery(getSQL.formatted(sentence), id);
        if (resultSet.next()) {
            return initEvent(resultSet);
        }
        return null;
    }

    @Nullable
    @Override
    public Event get(String name) throws Exception {
        String sentence = "WHERE `%s` = ?".formatted(titleHead);
        ResultSet resultSet = super.executeQuery(getSQL.formatted(sentence), name);
        if (resultSet.next()) {
            return initEvent(resultSet);
        }
        return null;
    }

    @Override
    public List<Event> getAll() throws Exception {
        List<Event> events = new ArrayList<>();
        ResultSet resultSet = super.executeQuery(getSQL.formatted("*", ""));
        while (resultSet.next()) {
            events.add(initEvent(resultSet));
        }
        return events;
    }

    @NotNull
    private Event initEvent(@NotNull ResultSet resultSet) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<Integer> list = new ArrayList<>();
        Event event = new Event(resultSet.getInt(idHead), resultSet.getString(titleHead), resultSet.getString(timeHead), resultSet.getString(meaningHead));
        ResultSet set = super.executeQuery("SELECT `%s` FROM `%s` WHERE `%s` = ?;".formatted(rocIdHead, ETRTable, idHead), event.getId());
        while (set.next()) {
            list.add(set.getInt(rocIdHead));
        }
        for (var i : list) {
            set = super.executeQuery("SELECT `%s` FROM `%s` WHERE `%s` = ?;".formatted(rocNameHead, rocTableName, rocIdHead), i);
            set.next();
            stringBuffer.append(set.getString(rocNameHead)).append(" ");
        }
        event.setRocketName(stringBuffer.toString().strip());
        stringBuffer.delete(0, stringBuffer.length());
        list.clear();
        set = super.executeQuery("SELECT `%s` FROM `%s` WHERE `%s` = ?;".formatted(astIdHead, ETATable, idHead), event.getId());
        while (set.next()) {
            list.add(set.getInt(astIdHead));
        }
        for (var i : list) {
            set = super.executeQuery("SELECT `%s` FROM `%s` WHERE `%s` = ?;".formatted(astNameHead, astTableName, astIdHead), i);
            set.next();
            stringBuffer.append(set.getString(astNameHead)).append(" ");
        }
        event.setAstronauts(stringBuffer.toString().strip());
        return event;
    }

    public List<Event> getAll(int id) throws Exception {
        List<Event> events = new ArrayList<>();
        ResultSet resultSet = super.executeQuery(getSQL.formatted("WHERE `%s` = ?".formatted(idHead)), id);
        while (resultSet.next()) {
            events.add(initEvent(resultSet));
        }
        return events;
    }

    public List<Event> getAll(String name) throws Exception {
        List<Event> events = new ArrayList<>();
        ResultSet resultSet = super.executeQuery(getSQL.formatted("WHERE `%s` LIKE ?".formatted(titleHead)), "%%%s%%".formatted(name));
        while (resultSet.next()) {
            events.add(initEvent(resultSet));
        }
        return events;
    }
}
