package com.example.findmypet.Models;

public class ProfileModel {

    private String photo, username, name, bio;
    private int follower_number,following_number, post_number;

    public ProfileModel(String username, String name, String photo, String bio, int follower_number, int following_number, int post_number) {
        this.photo = photo;
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.follower_number = follower_number;
        this.following_number = following_number;
        this.post_number = post_number;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
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

    public int getPost_number() {
        return post_number;
    }

    public void setPost_number(int post_number) {
        this.post_number = post_number;
    }
}
