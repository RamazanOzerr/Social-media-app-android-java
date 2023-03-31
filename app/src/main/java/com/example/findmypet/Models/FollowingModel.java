package com.example.findmypet.Models;

public class FollowingModel {

    private String username, photo, name;

    public FollowingModel(String username, String photo, String name) {
        this.username = username;
        this.photo = photo;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
