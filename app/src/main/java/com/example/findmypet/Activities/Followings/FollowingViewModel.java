package com.example.findmypet.Activities.Followings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FollowingViewModel extends ViewModel {

    private MutableLiveData<List<FollowingModel>> liveData;
    private List<FollowingModel> followingModelList;

    public FollowingViewModel(){
        liveData = new MutableLiveData<>();
        init();
        liveData.setValue(followingModelList);
    }

    public MutableLiveData<List<FollowingModel>> getFollowings(){
        return liveData;
    }

    private void init() {
        followingModelList = new ArrayList<>();
        populateList();
    }

    private void populateList() {
        FollowingModel following = new FollowingModel("azims","ft","azim");
        FollowingModel following2 = new FollowingModel("eren","ft","eren");
        FollowingModel following3 = new FollowingModel("ramo","ft","eren");
        FollowingModel following4 = new FollowingModel("beergay","ft","beergay");
        followingModelList.add(following);
        followingModelList.add(following2);
        followingModelList.add(following3);
        followingModelList.add(following4);
        followingModelList.add(following);
        followingModelList.add(following3);
        followingModelList.add(following4);
    }
}
