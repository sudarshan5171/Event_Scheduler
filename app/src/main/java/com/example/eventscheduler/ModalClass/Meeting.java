package com.example.eventscheduler.ModalClass;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "meeting")
public class Meeting {

    @PrimaryKey(autoGenerate = true)
    private int meetingId;

    private String meetingName;
    private String startTime;
    private String endTime;
    private ArrayList<Participant> participants;


    public Meeting(String meetingName, String startTime, String endTime, ArrayList<Participant> participants) {
        this.meetingName = meetingName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }
}
