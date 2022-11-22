package com.example.eventscheduler.Database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;


import com.example.eventscheduler.Dao.MeetingDao;
import com.example.eventscheduler.Dao.ParticipantDao;
import com.example.eventscheduler.ModalClass.Meeting;
import com.example.eventscheduler.ModalClass.Participant;

@Database(entities = {Participant.class, Meeting.class}, exportSchema = false,version = 1)
@TypeConverters({Converters.class})
public abstract class MyDatabase extends RoomDatabase {

    private static final String DB_NAME= "myDB";
    private static MyDatabase instance;

    public static synchronized MyDatabase getDB(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context,MyDatabase.class,DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract ParticipantDao ParticipantDao();
    public abstract MeetingDao MeetingDao();
}

