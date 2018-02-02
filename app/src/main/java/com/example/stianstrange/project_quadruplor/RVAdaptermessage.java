package com.example.stianstrange.project_quadruplor;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RVAdaptermessage extends RecyclerView.Adapter<RVAdaptermessage.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView messagecontenttextview;
        TextView personAge;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            messagecontenttextview = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
        }
    }

    List<Message> messages;

    RVAdaptermessage(List<Message> messages) {this.messages = messages;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.messagecontenttextview.setText(messages.get(i).messagecontent);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}