package com.example.findmypet.Models;

public class NotificationModel {

    private String photo;
    private String name;
    private String body;
    //todo: time ekliycez

    public NotificationModel(String photo, String name, String body) {
        this.photo = photo;
        this.name = name;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
