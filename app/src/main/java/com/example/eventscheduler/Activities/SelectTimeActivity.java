package com.example.eventscheduler.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eventscheduler.Database.MyDatabase;
import com.example.eventscheduler.ModalClass.Participant;
import com.example.eventscheduler.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SelectTimeActivity extends AppCompatActivity {



    Button btnStartTime, btnEndTime, btnAddParticipants;
    EditText etStart, etEnd, etMeetingName;
    Date startTime,endTime;
    public static String dateFormat = "hh:mm a  dd-MM-yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_time_activity);
        //create participants database instance


        //initialize views
        initializeViews();

        //initialize times
        initializeTime();

        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTime(0);
            }
        });
        btnEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTime(1);
            }
        });
        btnAddParticipants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectParticipants();
            }
        });
    }



    private void initializeViews() {
        btnStartTime=(Button)findViewById(R.id.btn_start_time);
        btnEndTime=(Button)findViewById(R.id.btn_end_time);
        etStart=(EditText)findViewById(R.id.start_date_time);
        etEnd=(EditText)findViewById(R.id.end_date_time);
        etMeetingName=(EditText)findViewById(R.id.meeting_name);
        btnAddParticipants=(Button)findViewById(R.id.btn_add_participants);
    }

    private void initializeTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        startTime= new Date();
        etStart.setText(sdf.format(startTime));
        endTime= new Date();
        etEnd.setText(sdf.format(endTime));
    }

    private void selectParticipants(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            startTime = sdf.parse(etStart.getText().toString());
            endTime = sdf.parse(etEnd.getText().toString());
        } catch (Exception e){
            e.printStackTrace();
        }

        //if start time is less than current time

        Date currTime=new Date();
        if(startTime.compareTo(currTime)>0){
            Toast.makeText(this, "Start time can't be less than current time", Toast.LENGTH_SHORT).show();
            return;
        }

        //if end time lesser than start time
        if(startTime.compareTo(endTime)>0){
            Toast.makeText(this, "End time can not be less than start time", Toast.LENGTH_SHORT).show();
            return;
        }

        //if name is empty
        String meetingName = etMeetingName.getText().toString();
        if(meetingName.length()<1){
            Toast.makeText(this, "Please enter name of meeting", Toast.LENGTH_SHORT).show();
            return;
        }

        //move to another page
        Bundle bundle = new Bundle();
        bundle.putString("meetingName",meetingName);
        bundle.putString("startTime",startTime.toString());
        bundle.putString("endTime",endTime.toString());
        Intent intent = new Intent(SelectTimeActivity.this, AddParticipantsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);  //sending bundle
    }

    public void selectTime(int val) {
        Calendar c = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                c.set(Calendar.YEAR,year);
                c.set(Calendar.MONTH,month);
                c.set(Calendar.DAY_OF_MONTH,day);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        c.set(Calendar.HOUR_OF_DAY,hour);
                        c.set(Calendar.MINUTE,minute);

                        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                        if(val==0){  //selecting start time
                            etStart.setText(sdf.format(c.getTime()));
                        }
                        else if(val==1){  //selecting end time
                            etEnd.setText(sdf.format(c.getTime()));
                        }
                    }
                };
                new TimePickerDialog(SelectTimeActivity.this,timeSetListener,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(SelectTimeActivity.this,dateSetListener,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
    }

    
}