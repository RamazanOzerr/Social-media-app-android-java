package com.example.findmypet.Activities.Comments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.ArrayList;
import java.util.List;

public class CommentsViewModel extends ViewModel {

    private List<CommentModel> commentModelList;
    private MutableLiveData<List<CommentModel>> liveData;
    public CommentsViewModel(){
        liveData = new MutableLiveData<>();
        init();
    }

    private void init(){
        commentModelList = new ArrayList<>();
        populateList();
        liveData.setValue(commentModelList);
    }

    private void populateList() {
        //todo: db den comment verilerini çek

        CommentModel commentModel = new CommentModel("fatih_terim","gs şampuan olacak","ft");
        commentModelList.add(commentModel);
        commentModelList.add(commentModel);
        commentModelList.add(commentModel);
        commentModelList.add(commentModel);
        commentModelList.add(commentModel);
        commentModelList.add(commentModel);

    }

    public LiveData<List<CommentModel>> getComments(){return liveData;}
}

