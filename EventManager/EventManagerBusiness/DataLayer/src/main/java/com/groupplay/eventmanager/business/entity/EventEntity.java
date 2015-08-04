package com.groupplay.eventmanager.business.entity;

import sun.security.tools.TimestampedSigner;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 15-07-04
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventEntity {


    private String id;

    private String groupId;

    private String eventTitle;

    private String eventContent;

    private Timestamp eventTime;

    //@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private EventLocationEntity eventLocation;

    private boolean isTemporary;

    private Timestamp deadline;

    private Timestamp createdTime;

    private boolean isDeleted;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    public EventLocationEntity getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(EventLocationEntity eventLocation) {
        this.eventLocation = eventLocation;
    }

    public boolean isTemporary() {
        return isTemporary;
    }

    public void setTemporary(boolean temporary) {
        isTemporary = temporary;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
