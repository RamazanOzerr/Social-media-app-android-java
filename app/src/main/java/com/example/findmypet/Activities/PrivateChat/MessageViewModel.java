package com.example.findmypet.Activities.PrivateChat;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MessageViewModel extends ViewModel {

    private MutableLiveData<List<MessageModel>> messageLiveData;

    public MessageViewModel(String currUser, String otherUser){
        messageLiveData = new MutableLiveData<>();
//        MessageRepository repository = new MessageRepository();
//        //todo: burayı düzenlicez
//        messageLiveData =repository.getMessages(currUser,otherUser);
        System.out.println("curr user: "+currUser);
        System.out.println("otherUser: "+otherUser);
    }

    public MutableLiveData<List<MessageModel>> getMessages(){
        return messageLiveData;
    }
}
