package com.example.eventscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AddParticipantsActivity extends AppCompatActivity {
    String startTime,endTime,meetingName;
    public static String dateFormat = "hh:mm a  dd-MM-yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participants);

        //get bundle from intent
        SimpleDateFormat sdf=new SimpleDateFormat();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            startTime = bundle.getString("startTime");
            endTime = bundle.getString("endTime");
            meetingName = bundle.getString("meetingName");
        }

        //show all the participants
        ArrayList<Participant> arrayList = getList();
        ListviewAdapter listviewAdapter = new ListviewAdapter(getApplicationContext(),arrayList);
        ListView listView = findViewById(R.id.list_view);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(listviewAdapter);
    }
    
    //return an array of participants
    private ArrayList<Participant> getList() {
        ArrayList<Participant> list = new ArrayList<>();

        for(int i=1;i<=10;i++){
            Participant p = new Participant(i,startTime,endTime,"Participant "+i,"alpha"+i+"@gmail.com");
            list.add(p);
        }
        return list;
    }


}