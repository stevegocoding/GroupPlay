package com.groupplay.eventmanager.business.dao;

import com.groupplay.eventmanager.business.entity.EventEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 15-07-04
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EventManagerDAO {

    void createEvent(EventEntity eventEntity);

    EventEntity loadEvent(String id);

    void updateEvent(EventEntity eventEntity);

    void deleteEvent(String id);

}
