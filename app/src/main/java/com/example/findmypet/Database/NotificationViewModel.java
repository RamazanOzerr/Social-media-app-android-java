package com.example.findmypet.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotificationViewModel extends AndroidViewModel {

    private NotificationRepository notificationRepository;
    private LiveData<List<NotificationEntity>> allNotifications;
    //private LiveData<List<NotificationEntity>> oneNotification;


    public NotificationViewModel(@NonNull Application application) {
        super(application);
        notificationRepository = new NotificationRepository(application);
        allNotifications =  notificationRepository.getAllNotifications();
    }

    public LiveData<List<NotificationEntity>> getAllNotifications() {
        return allNotifications;
    }

    public void insert(NotificationEntity notification) {
        notificationRepository.insert(notification);
    }

}
