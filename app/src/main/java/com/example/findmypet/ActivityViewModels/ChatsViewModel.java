package com.example.findmypet.ActivityViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.findmypet.Models.ChatModel;

import java.util.ArrayList;
import java.util.List;

public class ChatsViewModel extends ViewModel {
    private final MutableLiveData<List<ChatModel>> chatsModel;
    private List<ChatModel> chatsModelList;

    public ChatsViewModel() {
        chatsModel = new MutableLiveData<>();
        init();
    }

    private void init(){
        chatsModelList = new ArrayList<>();
        populateList();
        chatsModel.setValue(chatsModelList);
    }

    //todo: db den verileri çekip burda set etcez
    private void populateList(){
        ChatModel chatModel = new ChatModel("photo","fatih terim","merhaba koçum");
        chatsModelList.add(chatModel);
        chatsModelList.add(chatModel);
        chatsModelList.add(chatModel);
        chatsModelList.add(chatModel);
        chatsModelList.add(chatModel);
        chatsModelList.add(chatModel);
        chatsModelList.add(chatModel);
        chatsModelList.add(chatModel);
    }

    public LiveData<List<ChatModel>> getNotifications() {
        return chatsModel;
    }
}


