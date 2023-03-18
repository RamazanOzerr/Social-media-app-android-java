package com.example.findmypet.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotificationDAO {


    @Insert
    void insert(NotificationEntity notifications);


    @Query("Select * From findMyPet_app")
    List<NotificationEntity> getNotifications();

    //todo later add delete
}
