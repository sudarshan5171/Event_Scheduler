package com.example.eventscheduler.Activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.example.eventscheduler.Adapters.ListviewAdapter;
import com.example.eventscheduler.Database.MyDatabase;
import com.example.eventscheduler.ModalClass.Meeting;
import com.example.eventscheduler.ModalClass.Participant;
import com.example.eventscheduler.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        //show selected participants participants
        participantsList = getRemainingParticipants();

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

    private ArrayList<Participant> getRemainingParticipants() {
        //get all participants
        ArrayList<Participant>allParticipants = (ArrayList<Participant>) myDatabase.ParticipantDao().getParticipants();
        ArrayList<Participant>result = new ArrayList<>();
        //remove matching candidates
        ArrayList<Meeting>allmeetings = (ArrayList<Meeting>) myDatabase.MeetingDao().getMeeetings();
        ArrayList<Integer>deleteId = new ArrayList<>();  //to store the participants excluded

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date s=null,e=null;
        try {
            s = sdf.parse(startTime) ;
            e = sdf.parse(endTime);
        } catch (Exception exception){
            exception.printStackTrace();
        }
        
        for(int i=0;i<allmeetings.size();i++){
            Meeting meeting = allmeetings.get(i);
            Date s1=null,e1=null;
            try {
                s1 = sdf.parse(meeting.getStartTime());
                e1 = sdf.parse(meeting.getEndTime());
            } catch (Exception exception){
                exception.printStackTrace();
            }
            //if s>s1 and s<e1  or  e>s1 and e<e1  then meeting clash

            if((s.compareTo(s1)>0 && s.compareTo(e1)<0) || (e.compareTo(s1)>0 && e.compareTo(e1)<0)){
                //get participants of meeting
                ArrayList<Participant>participants = meeting.getParticipants();
                for (Participant p : participants) {
                    System.out.println( p.getParticipantId());
                    deleteId.add(p.getParticipantId());
                }
            }
        }

        for(int j=0;j<allParticipants.size();j++){
            Participant p = allParticipants.get(j);
            int currId=p.getParticipantId();

            if (!deleteId.contains(currId)) {
                System.out.println("added"+currId);
                result.add(p);
            }
        }
        return result;
    }

    private void scheduleMeeting() {
        ArrayList<Participant> selectedParticipants= new ArrayList<>();
        //select only checked candidates
        for(int i=0;i<participantsList.size();i++){
            Participant curr= participantsList.get(i);
            if(curr.isSelected()){
                selectedParticipants.add(curr);
            }
        }
        
        if(selectedParticipants.size()<2){
            Toast.makeText(this, "Please select atleast 2 candidates", Toast.LENGTH_SHORT).show();
            return;
        }
        
        //create a new meeting with selected participants
        Meeting meeting = new Meeting(meetingName,startTime,endTime,selectedParticipants);
        myDatabase.MeetingDao().addMeeting(meeting);

        Intent intent = new Intent(getApplicationContext(),MyMeetings.class);
        startActivity(intent);
    }
}