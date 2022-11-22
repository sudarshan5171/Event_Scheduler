package com.example.eventscheduler.ModalClass;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "participant")
public class Participant {

    @PrimaryKey(autoGenerate = true)
    private int participantId;

    private String name;
    private String email;
    private boolean selected=false;


    public Participant(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
