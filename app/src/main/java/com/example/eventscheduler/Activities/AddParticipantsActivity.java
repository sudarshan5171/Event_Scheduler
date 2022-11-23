package com.example.eventscheduler.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.eventscheduler.Adapters.ListviewAdapter;
import com.example.eventscheduler.Database.MyDatabase;
import com.example.eventscheduler.ModalClass.Meeting;
import com.example.eventscheduler.ModalClass.Participant;
import com.example.eventscheduler.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddParticipantsActivity extends AppCompatActivity {

    public MyDatabase myDatabase;
    String startTime,endTime,meetingName;
    FloatingActionButton btnAddMeeting;
    ArrayList<Participant> participantsList;

    public String dateFormat = "hh:mm a  dd-MM-yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participants);

        btnAddMeeting = findViewById(R.id.floatingActionButton);

        myDatabase = MyDatabase.getDB(this);  //get instance

        //get bundle from intent
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            startTime = bundle.getString("startTime");
            endTime = bundle.getString("endTime");
            meetingName = bundle.getString("meetingName");
        }

        //show all the participants
        participantsList = (ArrayList<Participant>) myDatabase.ParticipantDao().getParticipants();

        ListviewAdapter listviewAdapter = new ListviewAdapter(getApplicationContext(),participantsList);
        ListView listView = findViewById(R.id.list_view);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(listviewAdapter);

        btnAddMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleMeeting();
            }
        });
    }

    private void scheduleMeeting() {

        ArrayList<Participant> selectedParticipants= new ArrayList<>();

        //find selected participants
        for(int i=0;i<participantsList.size();i++){
            Participant curr= participantsList.get(i);
            if(curr.isSelected()){
                selectedParticipants.add(curr);
            }
        }

        //create a new meeting with selected participants
        Meeting meeting = new Meeting(meetingName,startTime,endTime,selectedParticipants);
        myDatabase.MeetingDao().addMeeting(meeting);

        Intent intent = new Intent(getApplicationContext(),MyMeetings.class);
        startActivity(intent);
    }

}