package com.groupplay.eventmanager.business.service;

import com.groupplay.eventmanager.business.dto.EventInfo;
import com.groupplay.eventmanager.business.dto.EventLocationInfo;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 15-08-01
 * Time: 8:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EventManagementService {

    /**
     * create the event after creating the group, or inside the group
     * @param groupId
     * @param eventInfo
     */
    void createEvent(String groupId, EventInfo eventInfo);

    /**
     * modify event only can be used inside the group, pick the event from the list first, then modify it.
     * @param eventInfo
     */
    void modifyEvent(String groupId, String eventId, EventInfo eventInfo);

    /**
     *   modify event only can be used inside the group, pick the event from the list first, then modify the location.
     * @param groupId
     * @param eventId
     * @param eventLocationInfo
     */
    void modifyEventLocation(String groupId, String eventId, EventLocationInfo eventLocationInfo);

    /**
     * delete the group after getting the event id
     * @param eventId
     */
    void deleteEvent(String eventId);

    /**
     * find the event by id
     * @param eventId
     * @return
     */
    EventInfo findEvent(String eventId);

    /**
     * search all the active event inside the group
     * @param groupId
     * @return
     */
    List<EventInfo> findEventAllActiveForGroup(String groupId, boolean isActive);

    /**
     * search all the  event inside the group
     * @param groupId
     * @return
     */
    List<EventInfo> findEventAllForGroup(String groupId);

    /**
     * map <groupId, eventInfo> to return the eventInfo list
     * @return
     */
    Map<String ,EventInfo> findEventAllActivePerUser();

    /**
     * find all the events including inactive ones
     * @return
     */
    Map<String ,EventInfo> findEventAllPerUser();
}
