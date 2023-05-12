package com.example.findmypet.Activities.Chats;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
        ChatModel chatModel1 = new ChatModel("photo","fatih terim","merhaba koçum", "online", "id1");
        ChatModel chatModel2 = new ChatModel("photo","leonel messi","merhaba koçum", "offline", "id2");
        chatsModelList.add(chatModel1);
        chatsModelList.add(chatModel2);
        chatsModelList.add(chatModel1);
        chatsModelList.add(chatModel1);
        chatsModelList.add(chatModel2);
        chatsModelList.add(chatModel1);
        chatsModelList.add(chatModel2);
        chatsModelList.add(chatModel2);
        chatsModelList.add(chatModel2);
    }

    public LiveData<List<ChatModel>> getChats() {
        return chatsModel;
    }
}


