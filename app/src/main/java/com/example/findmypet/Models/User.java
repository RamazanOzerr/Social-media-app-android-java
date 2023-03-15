package com.example.findmypet.Models;

public class User {

    private String username;
    private String photo;
    private String name;
    private String bio;
    private int follower_number;
    private int following_number;

    public User(String username, String photo, String name, String bio, int follower_number, int following_number) {
        this.username = username;
        this.photo = photo;
        this.name = name;
        this.bio = bio;
        this.follower_number = follower_number;
        this.following_number = following_number;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getFollower_number() {
        return follower_number;
    }

    public void setFollower_number(int follower_number) {
        this.follower_number = follower_number;
    }

    public int getFollowing_number() {
        return following_number;
    }

    public void setFollowing_number(int following_number) {
        this.following_number = following_number;
    }
}
