package com.example.eventscheduler.Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.eventscheduler.ModalClass.Participant;

import java.util.List;

@Dao
public interface ParticipantDao {

    @Query("select * from Participant")
    List<Participant> getParticipants();

    @Insert
    void addParticipant(Participant p);

    @Update
    void updateParticipant(Participant p);

    @Delete
    void deleteParticipant(Participant p);
}
