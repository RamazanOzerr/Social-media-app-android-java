package com.example.findmypet.Models;

public class VideoPost {
    private String post_video;
    private String user_profile_photo;
    private String username;
    private int total_likes;
    private int total_comments;

    public VideoPost(String post_video, String user_profile_photo, String username, int total_likes, int total_comments) {
        this.post_video = post_video;
        this.user_profile_photo = user_profile_photo;
        this.username = username;
        this.total_likes = total_likes;
        this.total_comments = total_comments;
    }

    public String getPost_video() {
        return post_video;
    }

    public void setPost_video(String post_video) {
        this.post_video = post_video;
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
