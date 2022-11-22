package com.example.eventscheduler.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.eventscheduler.ModalClass.Meeting;

import java.util.List;

@Dao
public interface MeetingDao {

    @Query("select * from Meeting")
    List<Meeting> getParticipants();

    @Insert
    void addMeeting(Meeting m);

    @Update
    void updateMeeting(Meeting m);

    @Delete
    void deleteMeeting(Meeting m);
}
