package com.example.eventscheduler.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventscheduler.Database.MyDatabase;
import com.example.eventscheduler.ModalClass.Participant;
import com.example.eventscheduler.R;

public class MainActivity extends AppCompatActivity {
    String prevStarted="yes";
    public static MyDatabase myDatabase;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if(!sharedPreferences.getBoolean(prevStarted,false)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(prevStarted,Boolean.TRUE);
            editor.apply();

            myDatabase = MyDatabase.getDB(this);
            createParticipantsDatabase();
        }
        else {
            moveToMeetingActivity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_time_activity);

        Intent intent = new Intent(this,SelectTimeActivity.class);
        startActivity(intent);
    }

    private void moveToMeetingActivity() {
        Intent intent = new Intent(this,MyMeetings.class);
        startActivity(intent);
    }

    private void createParticipantsDatabase() {
        for(int i=1;i<=20;i++){
            String name = "Alpha Singh "+ i;
            String mail = "alpha"+i+"@gmail.com";
            Participant p=new Participant(name,mail);
            myDatabase.ParticipantDao().addParticipant(p);
        }
    }
}
