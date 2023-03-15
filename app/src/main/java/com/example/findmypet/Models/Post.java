package com.example.findmypet.Models;

public class Post {
    private String post_media;
    private String user_profile_photo;
    private String username;
    private int total_likes;
    private int total_comments;
    private String type;

    public Post(String post_media, String user_profile_photo, String username,
                int total_likes, int total_comments, String type) {
        this.post_media = post_media;
        this.user_profile_photo = user_profile_photo;
        this.username = username;
        this.total_likes = total_likes;
        this.total_comments = total_comments;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPost_media() {
        return post_media;
    }

    public void setPost_media(String post_media) {
        this.post_media = post_media;
    }

    public String getUser_profile_photo() {
        return user_profile_photo;
    }

    public void setUser_profile_photo(String user_profile_photo) {
        this.user_profile_photo = user_profile_photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotal_likes() {
        return total_likes;
    }

    public void setTotal_likes(int total_likes) {
        this.total_likes = total_likes;
    }

    public int getTotal_comments() {
        return total_comments;
    }

    public void setTotal_comments(int total_comments) {
        this.total_comments = total_comments;
    }
}
