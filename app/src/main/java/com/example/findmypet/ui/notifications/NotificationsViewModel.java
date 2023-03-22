package com.example.findmypet.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.findmypet.Models.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<List<NotificationModel>> notificationModels;
    private List<NotificationModel> notificationModelList;

    public NotificationsViewModel() {
        notificationModels = new MutableLiveData<>();
        init();
//        mText = new MutableLiveData<>();
//        mText.setValue("This is notifications fragment");
    }

    private void init(){
        notificationModelList = new ArrayList<>();
        populateList();
        notificationModels.setValue(notificationModelList);
    }

    //todo: db den verileri çekip burda set etcez
    private void populateList(){
        NotificationModel notificationModel = new NotificationModel("notification",
                "username","notification body");
        notificationModelList.add(notificationModel);
        notificationModelList.add(notificationModel);
        notificationModelList.add(notificationModel);
        notificationModelList.add(notificationModel);
        notificationModelList.add(notificationModel);
        notificationModelList.add(notificationModel);
        notificationModelList.add(notificationModel);
        notificationModelList.add(notificationModel);
    }

    public LiveData<List<NotificationModel>> getNotifications() {
        return notificationModels;
    }
}