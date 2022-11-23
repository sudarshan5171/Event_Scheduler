package com.example.eventscheduler.Activities;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyMeetings extends AppCompatActivity {

    ExpandableListView expandableListViewExample;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableTitleList;
    HashMap<String, List<String>> expandableDetailList;
    public MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_meetings);
        expandableListViewExample = (ExpandableListView) findViewById(R.id.meeting_list);

        myDatabase = MyDatabase.getDB(this);;  //getinstance

        expandableDetailList = getData();
        expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
        expandableListAdapter = new CustomListviewAdapter(this, expandableTitleList, expandableDetailList);
        expandableListViewExample.setAdapter(expandableListAdapter);

        // This method is called when the group is expanded
        expandableListViewExample.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });

        // This method is called when the group is collapsed
        expandableListViewExample.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
            }
        });

        // This method is called when the child in any group is clicked
        // via a toast method, it is shown to display the selected child item as a sample
        // we may need to add further steps according to the requirements
        expandableListViewExample.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), expandableTitleList.get(groupPosition)
                        + " -> "
                        + expandableDetailList.get(
                        expandableTitleList.get(groupPosition)).get(
                        childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });

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
}