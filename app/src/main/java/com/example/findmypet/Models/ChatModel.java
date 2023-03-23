package com.example.findmypet.Models;

public class ChatModel {

    private String photo, name, text;

    public ChatModel(String photo, String name, String text) {
        this.photo = photo;
        this.name = name;
        this.text = text;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
