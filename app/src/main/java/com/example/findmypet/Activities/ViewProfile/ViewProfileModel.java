package com.example.findmypet.Activities.ViewProfile;

import com.example.findmypet.Activities.ViewPosts.ViewProfilePostModel;

import java.util.List;

public class ViewProfileModel {

    private String photo, username, name, bio;
    private int follower_number,following_number, post_number;
    private List<ViewProfilePostModel> postModelList; // user a ait post ların listesi, bunları aynı
    // aktivite içerisinde recyclerView de göstercez

    public ViewProfileModel(String photo, String username, String name,
                            String bio, int follower_number, int following_number,
                            int post_number, List<ViewProfilePostModel> postModelList) {

        this.photo = photo;
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.follower_number = follower_number;
        this.following_number = following_number;
        this.post_number = post_number;
        this.postModelList = postModelList;
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

    public List<ViewProfilePostModel> getPostModelList() {
        return postModelList;
    }

    public void setPostModelList(List<ViewProfilePostModel> postModelList) {
        this.postModelList = postModelList;
    }
}
