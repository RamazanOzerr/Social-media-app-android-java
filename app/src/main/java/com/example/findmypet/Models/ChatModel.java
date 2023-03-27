package com.example.findmypet.Models;

public class ChatModel {

    private String photo, name, text, status;

    public ChatModel(String photo, String name, String text, String status) {
        this.photo = photo;
        this.name = name;
        this.text = text;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
