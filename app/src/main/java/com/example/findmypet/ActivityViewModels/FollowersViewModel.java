package com.example.findmypet.ActivityViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.findmypet.Models.FollowerModel;
import com.example.findmypet.databinding.FollowerItemBinding;

import java.util.ArrayList;
import java.util.List;

public class FollowersViewModel extends ViewModel {

    private MutableLiveData<List<FollowerModel>> liveData;
    private List<FollowerModel> followerModelList;

    public FollowersViewModel(){
        liveData = new MutableLiveData<>();
        init();
    }

    public MutableLiveData<List<FollowerModel>> getFollowers(){
        return liveData;
    }

    private void init(){
        followerModelList = new ArrayList<>();
        populateList();
        liveData.setValue(followerModelList);
    }

    private void populateList(){
        // get data from db
        FollowerModel follower = new FollowerModel("azims","ft","azim");
        FollowerModel follower2 = new FollowerModel("eren","ft","eren");
        FollowerModel follower3 = new FollowerModel("ramo","ft","eren");
        FollowerModel follower4 = new FollowerModel("beergay","ft","beergay");
        followerModelList.add(follower);
        followerModelList.add(follower2);
        followerModelList.add(follower3);
        followerModelList.add(follower4);
        followerModelList.add(follower);
        followerModelList.add(follower3);
        followerModelList.add(follower4);

    }
}
