package com.example.findmypet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.findmypet.R;

public class ChatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        Toast.makeText(ChatsActivity.this, "yes açıldı",Toast.LENGTH_SHORT).show();

    }
}