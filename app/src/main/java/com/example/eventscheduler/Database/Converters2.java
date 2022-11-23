package com.example.eventscheduler.Database;

import androidx.room.TypeConverter;

import com.example.eventscheduler.ModalClass.Meeting;
import com.example.eventscheduler.ModalClass.Participant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters2 {
    @TypeConverter
    public static ArrayList<Meeting> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Meeting>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Meeting> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
