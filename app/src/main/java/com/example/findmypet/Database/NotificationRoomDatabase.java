package com.example.findmypet.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NotificationEntity.class}, version = 1)
public abstract class NotificationRoomDatabase extends RoomDatabase {

    public abstract NotificationDAO notificationDAO();
    private static NotificationRoomDatabase INSTANCE;

    public static NotificationRoomDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (NotificationRoomDatabase.class) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotificationRoomDatabase.class, "notification_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration().build();
                }

            }
        }
        return INSTANCE;

    }
}
