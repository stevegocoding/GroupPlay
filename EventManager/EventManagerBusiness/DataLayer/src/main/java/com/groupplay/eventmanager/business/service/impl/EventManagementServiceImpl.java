package com.groupplay.eventmanager.business.service.impl;

import com.groupplay.eventmanager.business.dao.EventManagerDAO;
import com.groupplay.eventmanager.business.dto.EventInfo;
import com.groupplay.eventmanager.business.dto.EventLocationInfo;
import com.groupplay.eventmanager.business.entity.EventEntity;
import com.groupplay.eventmanager.business.entity.EventLocationEntity;
import com.groupplay.eventmanager.business.service.EventManagementService;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 15-08-03
 * Time: 8:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventManagementServiceImpl implements EventManagementService {


    @Inject
    @Named("eventManagerDAO")
    private EventManagerDAO eventManagerDAO;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    @Override
    public void createEvent(String groupId, EventInfo eventInfo) {
        EventEntity entity = new EventEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setEventTitle(eventInfo.getTitle());
        entity.setEventContent(eventInfo.getContent());
        entity.setTemporary(eventInfo.isTemporary());
        entity.setGroupId(groupId);
        entity.setEventTime(eventInfo.getEventTime());
        entity.setDeadline(eventInfo.getDeadline());
        if(eventInfo.getEventLocationInfo()!= null) {

            EventLocationEntity eventLocationEntity = new EventLocationEntity();
            eventLocationEntity.setId(UUID.randomUUID().toString());
            eventLocationEntity.setAddress_1(eventInfo.getEventLocationInfo().getAddress_1());
            eventLocationEntity.setAddress_2(eventInfo.getEventLocationInfo().getAddress_2());
            eventLocationEntity.setCity(eventInfo.getEventLocationInfo().getCity());
            eventLocationEntity.setUnit(eventInfo.getEventLocationInfo().getUnit());
            eventLocationEntity.setProvince(eventInfo.getEventLocationInfo().getProvince());
            eventLocationEntity.setPostcode(eventInfo.getEventLocationInfo().getPostcode());
            eventLocationEntity.setTel(eventInfo.getEventLocationInfo().getTel());
            eventLocationEntity.setName(eventInfo.getEventLocationInfo().getName());
            entity.setEventLocation(eventLocationEntity);
        }
        this.eventManagerDAO.createEvent(entity);

    }

    @Override
    public void modifyEvent(String groupId, String eventId, EventInfo eventInfo) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void modifyEventLocation(String groupId, String eventId, EventLocationInfo eventLocationInfo) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteEvent(String eventId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public EventInfo findEvent(String eventId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<EventInfo> findEventAllActiveForGroup(String groupId, boolean isActive) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<EventInfo> findEventAllForGroup(String groupId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, EventInfo> findEventAllActivePerUser() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, EventInfo> findEventAllPerUser() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
