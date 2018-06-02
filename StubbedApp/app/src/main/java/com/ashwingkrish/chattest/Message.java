package com.ashwingkrish.chattest;

public class Message {
    private String text, email;
    private long timestamp;

    public Message(String text, String email, long timestamp) {
        this.text = text;
        this.email = email;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }


    public String getEmail() {
        return email;
    }

    public long getTimestamp() {
        return timestamp;
    }


}
