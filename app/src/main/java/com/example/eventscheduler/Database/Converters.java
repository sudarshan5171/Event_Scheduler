package com.example.eventscheduler.Database;

import androidx.room.TypeConverter;

import com.example.eventscheduler.ModalClass.Participant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<Participant> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Participant>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Participant> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
