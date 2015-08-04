package com.groupplay.eventmanager.business.dto;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 15-08-03
 * Time: 5:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventInfo {

    private String eventId;

    private String title;

    private String content;

    private Timestamp eventTime;

    private boolean isTemporary;

    private Timestamp deadline;

    private EventLocationInfo eventLocationInfo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
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

    public EventLocationInfo getEventLocationInfo() {
        return eventLocationInfo;
    }

    public void setEventLocationInfo(EventLocationInfo eventLocationInfo) {
        this.eventLocationInfo = eventLocationInfo;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
