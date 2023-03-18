package com.example.findmypet.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class NotificationRepository {

    private NotificationDAO notificationDAO;
    private LiveData<List<NotificationEntity>> allNotifications;

    public NotificationRepository(Application application) {

        NotificationRoomDatabase db = NotificationRoomDatabase.getDatabase(application);
        notificationDAO = db.notificationDAO();
        allNotifications = (LiveData<List<NotificationEntity>>) notificationDAO.getNotifications();
    }

    public LiveData<List<NotificationEntity>> getAllNotifications() {

        return allNotifications;
    }

    public void insert(NotificationEntity notification)  {
        new insertAsyncTask(notificationDAO).execute(notification);
    }

    private static class insertAsyncTask extends AsyncTask<NotificationEntity, Void, Void> {
        private NotificationDAO mAsyncTaskDao;

        insertAsyncTask(NotificationDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final NotificationEntity ... notificationEntities) {
            for (NotificationEntity u : notificationEntities) {
                mAsyncTaskDao.insert(u);
            }

            return null;
        }


    }

}
