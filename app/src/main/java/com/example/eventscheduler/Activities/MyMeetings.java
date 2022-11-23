package com.example.eventscheduler.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.eventscheduler.Adapters.CustomListviewAdapter;
import com.example.eventscheduler.Database.MyDatabase;
import com.example.eventscheduler.ModalClass.Meeting;
import com.example.eventscheduler.ModalClass.Participant;
import com.example.eventscheduler.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyMeetings extends AppCompatActivity {

    ExpandableListView expandableListViewExample;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableTitleList;
    HashMap<String, List<String>> expandableDetailList;
    FloatingActionButton fab;
    public MyDatabase myDatabase;
    ArrayList<String>timeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_meetings);
        expandableListViewExample = (ExpandableListView) findViewById(R.id.meeting_list);

        myDatabase = MyDatabase.getDB(this);;  //getinstance

        expandableDetailList = getData();
        timeList = getTimeList();
        expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
        expandableListAdapter = new CustomListviewAdapter(this, expandableTitleList, expandableDetailList,timeList);
        expandableListViewExample.setAdapter(expandableListAdapter);
    }

    private ArrayList<String> getTimeList() {
        ArrayList<String>result = new ArrayList<>();
        ArrayList<Meeting>allMeetings = (ArrayList<Meeting>) myDatabase.MeetingDao().getMeeetings();

        for(int i=0;i<allMeetings.size();i++){
            Meeting m=allMeetings.get(i);
            result.add(m.getStartTime());
        }
        return result;
    }

    private HashMap<String, List<String>> getData() {
        ArrayList<Meeting>meetings = (ArrayList<Meeting>) myDatabase.MeetingDao().getMeeetings();
        HashMap<String, List<String>> result = new HashMap<>();

        //adding meetings
        for(int i=0;i<meetings.size();i++){
            Meeting meeting = meetings.get(i);
            String meetingName=meeting.getMeetingName();

            //adding names of participant of meeting m
            ArrayList<Participant> participants = meeting.getParticipants();
            ArrayList<String>participantNames = new ArrayList<>();
            for(int j=0;j<participants.size();j++){
                Participant participant = participants.get(j);
                participantNames.add(participant.getName());
            }
            result.put(meetingName,participantNames);
        }

        return result;
    }

    public void addNewMeeting(View view) {
        Intent intent = new Intent(this,SelectTimeActivity.class);
        startActivity(intent);
    }
}