package com.system.DAO.dao;

import com.system.DAO.polo.Event;

import java.util.List;

public interface EventDao extends BasicDAO {
    boolean addEvent(Event event) throws Exception;

    boolean updateEvent(Event event) throws Exception;

    boolean deleteEvent(int id) throws Exception;

    Event getEventByName(String name) throws Exception;

    List<Event> getAllEvents() throws Exception;
}
