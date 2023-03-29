package com.example.findmypet.ActivityViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.findmypet.Models.ViewLikesModel;

import java.util.ArrayList;
import java.util.List;

public class ViewLikesViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<ViewLikesModel>> mutableLiveData;
    private List<ViewLikesModel> viewLikesModelList;

    public ViewLikesViewModel(){
        mutableLiveData = new MutableLiveData<>();
        init();
    }

    private void init(){
        viewLikesModelList = new ArrayList<>();
        populateList();
        mutableLiveData.setValue(viewLikesModelList);
    }

    private void populateList() {
        ViewLikesModel model1 = new ViewLikesModel("fatih terim","fatih terim",
                "goat","ft");
        ViewLikesModel model2 = new ViewLikesModel("icardi","icardi",
                "goat","icardi");
        ViewLikesModel model3 = new ViewLikesModel("diagne","diagne",
                "goat","diagne");

        viewLikesModelList.add(model1);
        viewLikesModelList.add(model2);
        viewLikesModelList.add(model3);
    }

    public LiveData<List<ViewLikesModel>> getLikes(){return mutableLiveData;}
}