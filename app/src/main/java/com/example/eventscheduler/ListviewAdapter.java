package com.example.eventscheduler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListviewAdapter extends ArrayAdapter<Participant> {

    //constructor
    public ListviewAdapter(@NonNull Context context, ArrayList<Participant> arrayList){
        //pass context and arraylist for super constructor
        super(context,0,arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //getting the current view
        View currentItemView = convertView;
        if(currentItemView==null){
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Participant currParticipant = getItem(position);
        TextView pID = currentItemView.findViewById(R.id.participant_id);
        TextView pName = currentItemView.findViewById(R.id.participant_name);
        TextView pMail = currentItemView.findViewById(R.id.participant_mail);

        //setting the list item values
        pID.setText(Integer.toString(currParticipant.getParticipantId())+".");
        pName.setText(currParticipant.getName());
        pMail.setText("Email:"+ currParticipant.getEmail());

        return currentItemView;
    }
}
