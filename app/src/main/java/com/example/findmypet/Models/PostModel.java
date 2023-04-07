package com.example.findmypet.Models;

import java.util.List;

public class PostModel {

    private String username, profile_photo, url, type, comment_number,
            like_number, post_time;
    private List<CommentModel> comments;
    private List<ViewLikesModel> likes;

    public PostModel(String username, String profile_photo, String url, String type,
                     String comment_number, String like_number, String post_time,
                     List<CommentModel> comments, List<ViewLikesModel> likes) {
        this.username = username;
        this.profile_photo = profile_photo;
        this.url = url;
        this.type = type;
        this.comment_number = comment_number;
        this.like_number = like_number;
        this.post_time = post_time;
        this.comments = comments;
        this.likes = likes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment_number() {
        return comment_number;
    }

    public void setComment_number(String comment_number) {
        this.comment_number = comment_number;
    }

    public String getLike_number() {
        return like_number;
    }

    public void setLike_number(String like_number) {
        this.like_number = like_number;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    public List<ViewLikesModel> getLikes() {
        return likes;
    }

    public void setLikes(List<ViewLikesModel> likes) {
        this.likes = likes;
    }
}
