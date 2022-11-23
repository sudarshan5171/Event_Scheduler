package com.example.eventscheduler.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eventscheduler.ModalClass.Participant;
import com.example.eventscheduler.R;

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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_participant,parent,false);
        }

        Participant currParticipant = getItem(position);
        TextView pID = currentItemView.findViewById(R.id.participant_id);
        TextView pName = currentItemView.findViewById(R.id.participant_name);
        TextView pMail = currentItemView.findViewById(R.id.participant_mail);
        ImageView addIcon = currentItemView.findViewById(R.id.add_icon);

        //setting the list item values
        pID.setText(Integer.toString(currParticipant.getParticipantId())+". ");
        pName.setText(currParticipant.getName());
        pMail.setText("Email: "+ currParticipant.getEmail());
        addIcon.setVisibility(currParticipant.isSelected() ? View.VISIBLE  : View.GONE);

        currentItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currParticipant.setSelected(!currParticipant.isSelected());  //set opposite of previous state
                addIcon.setVisibility(currParticipant.isSelected() ? View.VISIBLE  : View.GONE);
            }
        });

        return currentItemView;
    }


}
