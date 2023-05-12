package com.example.findmypet.Activities.Post;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.findmypet.Models.Post;

import java.util.ArrayList;
import java.util.List;

public class PostViewModel extends ViewModel {

    private List<Post> postList;
    private MutableLiveData<List<Post>> liveData;

    public PostViewModel(){
        liveData = new MutableLiveData<>();
        init();
    }

    public MutableLiveData<List<Post>> getPosts(){
        return liveData;
    }

    private void init() {
        postList = new ArrayList<>();
        populateList();
        liveData.setValue(postList);
    }

    private void populateList() {
        Post post = new Post("ft","photo","fatih terim",
                2,5,"photo","I am the last bird fucker","online");
        Post post1 = new Post("icardi","photo","icardi",
                2,5,"photo","söylenmedi hiiiiiç","offline");
        Post post2 = new Post("diagne","photo","diagne",
                2,5,"photo","olmasaydı sonumuz böyle","online");
        Post post3 = new Post("falcao","photo","falcao",
                2,5,"photo","sakat doğdum sakat ölücem","online");

        postList.add(post);
        postList.add(post2);
        postList.add(post3);
        postList.add(post1);
        postList.add(post2);
        postList.add(post);
        postList.add(post2);
        postList.add(post3);
        postList.add(post1);
        postList.add(post2);
    }
}
