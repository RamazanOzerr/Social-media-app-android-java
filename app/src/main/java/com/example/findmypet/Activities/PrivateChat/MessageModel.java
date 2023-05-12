package com.example.findmypet.Activities.PrivateChat;

public class MessageModel {

    private String sender, text, time, type;
    private boolean seen;

    public MessageModel(String sender, String text, String time, String type, boolean seen) {
        this.sender = sender;
        this.text = text;
        this.time = time;
        this.type = type;
        this.seen = seen;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
