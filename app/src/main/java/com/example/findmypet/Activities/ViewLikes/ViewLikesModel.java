package com.example.findmypet.Activities.ViewLikes;

public class ViewLikesModel {

    private String username, name, bio, photo;

    public ViewLikesModel(String username, String name, String bio, String photo) {
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
