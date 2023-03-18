package com.example.findmypet.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "findMyPet_app")
public class NotificationEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "messageBody")
    private String messageBody;

    @ColumnInfo(name = "time")
    private Long time;

    @ColumnInfo(name = "photo")
    private String photo;

    public NotificationEntity() {}

    public NotificationEntity(String name, String messageBody, Long time, String photo) {
        this.name = name;
        this.messageBody = messageBody;
        this.time = time;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", time=" + time +
                ", photo='" + photo + '\'' +
                '}';
    }
}
