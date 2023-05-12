package com.example.findmypet.Activities.PrivateChat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findmypet.R;
import com.example.findmypet.databinding.ActivityPrivateChatBinding;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r0adkll.slidr.Slidr;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PrivateChatActivity extends AppCompatActivity {

    private ActivityPrivateChatBinding binding;
    private MessageViewModel viewModel;
    private String currUser, otherUser;

    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private AppCompatEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_chat);

        binding = ActivityPrivateChatBinding.inflate(getLayoutInflater());
        currUser = "fatih";
        otherUser = "terim";
        init();
        viewModel = new ViewModelProvider(this,
                new ViewModelFactory(currUser, otherUser)).get(MessageViewModel.class);
        Slidr.attach(this);

        setListeners();

//        Drawable drawable = AppCompatResources.getDrawable(this,R.drawable.shape_chat);
////        binding.constraintPrivateChat.setBackground(drawable);
//
//        binding.constraintPrivateChat
//                .setOnClickListener(view ->{
//                    Toast.makeText(this, "yeah im in it",Toast.LENGTH_SHORT).show();
//                    binding.constraintPrivateChat
//                            .setBackground(drawable);
//
//                });
//
//        ConstraintLayout constraintLayout = findViewById(R.id.constraint_private_chat);
//        constraintLayout.setOnClickListener(view -> {
//            Toast.makeText(this, "yeah im in it",Toast.LENGTH_SHORT).show();
//            System.out.println("yeah im in it");
//            binding.constraintPrivateChat
//                    .setBackground(drawable);
//
//
//        });

    }

    private void setListeners() {
        AppCompatImageView sendimg = findViewById(R.id.image_send_privatechat);
        sendimg.setOnClickListener(view -> {
            if(editText.getText() != null){
                String text = editText.getText().toString();
                String sender = currUser; //todo: bunu silcez
                String type = "text";
                boolean seen = false;
                String time = getTime();
                sendMessage(new MessageModel(sender, text, time, type, seen));
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this,
                        "you cannot send an empty message",Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void init(){
//        otherUser = getIntent().getStringExtra("otherUser");
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        if(firebaseUser != null){
//            currUser = firebaseUser.getUid();
//        }else{
//            //todo: user giriş yapmamış gibi gözüküyor, yeniden giriş yapmasının bir yolunu bul
//            // Zaman aşımına uğradı gibi bir şey söyleyip yeniden giriş yapmasını isteyebiliriz
//            currUser = "";
//        }
        editText = findViewById(R.id.edittext_privatechat);
        databaseReference = FirebaseDatabase.getInstance().getReference("Messages");
    }

    private void sendMessage(MessageModel message){
        String messageId = databaseReference.child(currUser).child(otherUser).push().getKey();
        if(messageId != null){
            databaseReference.child(currUser).child(otherUser)
                    .child(messageId).setValue(message).addOnCompleteListener(task -> {
                              databaseReference.child(otherUser).child(currUser).child(messageId)
                                      .setValue(message).addOnCompleteListener(task1 -> {
                                          editText.requestFocus();
                                          editText.setText("");
                                          Toast.makeText(this,
                                                  "message sent successfully",
                                                  Toast.LENGTH_SHORT).show();
                                      });
            });
        }
    }

    private String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }
}