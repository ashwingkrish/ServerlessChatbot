package com.ashwingkrish.chattest;


import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private List<Message> messageList;
    private DateFormat df = new SimpleDateFormat("", Locale.ENGLISH);

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text, email, time;

        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.tv_message_text);
            time = view.findViewById(R.id.tv_message_time);
            email = view.findViewById(R.id.tv_message_email);
        }
    }


    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_message, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Message msg = messageList.get(position);
        holder.text.setText(msg.getText());
        holder.time.setText(df.format(new Date(msg.getTimestamp())));
        holder.email.setText(msg.getEmail());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}