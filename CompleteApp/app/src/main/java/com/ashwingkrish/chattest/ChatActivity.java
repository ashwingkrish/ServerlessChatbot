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
    DatabaseReference messagesReference;
    static boolean active = false;

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            long timestamp = dataSnapshot.child("timestamp").getValue(Long.class);
            String email = dataSnapshot.child("email").getValue().toString();
            String text = dataSnapshot.child("text").getValue().toString();
            messageList.add(new Message(text, email, timestamp));
            messageAdapter.notifyItemInserted(messageList.size()-1);
            rv.scrollToPosition(messageList.size() - 1);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        }
        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }
        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

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

        messagesReference = FirebaseDatabase.getInstance().getReference().child("messages");
        messagesReference.limitToLast(10).addChildEventListener(childEventListener);
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
                    HashMap<String, Object> childMap = new HashMap<>();
                    String key = messagesReference.push().getKey();
                    childMap.put("email", user.getEmail());
                    childMap.put("timestamp", System.currentTimeMillis());
                    childMap.put("text", msg);
                    messagesReference.child(key).updateChildren(childMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if (databaseError != null) {
                                Toast.makeText(ChatActivity.this, "Database error: "+databaseError, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

}
