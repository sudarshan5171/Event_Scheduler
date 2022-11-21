package com.example.eventscheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Participant {
    String start,end;
    String name;
    String email;
    int participantId;

    public Participant( int participantId, String start, String end, String name, String email) {
        this.start = start;
        this.end = end;
        this.name = name;
        this.email = email;
        this.participantId = participantId;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getParticipantId() {
        return participantId;
    }
}
