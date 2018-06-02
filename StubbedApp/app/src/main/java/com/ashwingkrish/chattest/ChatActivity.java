package com.ashwingkrish.chattest;

import android.app.NotificationManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {

    RecyclerView rv;
    ImageButton ib_send;
    EditText et_chat;
    ArrayList<Message> messageList = new ArrayList<>();
    MessageAdapter messageAdapter = new MessageAdapter(messageList);

    static boolean active = false;

    // TODO: Define child event listener and reference to the messages


    @Override
    protected void onStart() {
        super.onStart();
        active = true;
        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.cancel(MyFirebaseMessagingService.notificationId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        active = false;
    }

    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        rv = findViewById(R.id.rv_messages);
        ib_send = findViewById(R.id.ib_chat_send);
        et_chat = findViewById(R.id.et_chat);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            finish();
            return;
        }


        // TODO: Initialize reference to the messages node of the database

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        rv.setLayoutManager(mLayoutManager);


        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(messageAdapter);

        ib_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = et_chat.getText().toString().trim();
                if (!msg.isEmpty()) {
                    et_chat.setText("");

                    // TODO: Add the message to the database
                }
            }
        });
    }

}
