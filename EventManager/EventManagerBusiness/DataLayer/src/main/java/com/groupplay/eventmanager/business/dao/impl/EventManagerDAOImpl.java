package com.groupplay.eventmanager.business.dao.impl;

import com.apple.laf.AquaButtonBorder;
import com.groupplay.eventmanager.business.dao.EventManagerDAO;
import com.groupplay.eventmanager.business.entity.EventEntity;
import com.groupplay.eventmanager.business.entity.EventLocationEntity;

import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 15-07-04
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */

@Named("eventManagerDao")
public class EventManagerDAOImpl implements EventManagerDAO {

    @Override
    public void createEvent(EventEntity eventEntity) {
        //To change body of implemented methods use File | Settings | File Templates.
        //test  for detailed info
    }

    @Override
    public EventEntity loadEvent(String id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateEvent(EventEntity eventEntity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateEventLocation(EventLocationEntity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteEvent(String id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addEventLocation(String eventId, EventLocationEntity eventLocationEntity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
