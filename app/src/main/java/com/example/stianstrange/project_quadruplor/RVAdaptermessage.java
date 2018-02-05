package com.example.stianstrange.project_quadruplor;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;
public class RVAdaptermessage extends RecyclerView.Adapter<RVAdaptermessage.MessageViewHolder> {

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView messagecontent;

        MessageViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            messagecontent = (TextView)itemView.findViewById(R.id.messagecontent);
        }
    }
        List<MES> messages;
        RVAdaptermessage(List<MES> messages) {
      this.messages = messages;}


//     List<MES> messages;
//
//     public RVAdaptermessage(List<MES> messages) {
//        this.messages = messages;
//    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        MessageViewHolder pvh = new MessageViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder messageViewHolder, int position) {
        messageViewHolder.messagecontent.setText(messages.get(position).messagecontent);

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}