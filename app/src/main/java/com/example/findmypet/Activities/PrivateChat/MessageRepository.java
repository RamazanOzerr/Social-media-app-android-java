package com.example.findmypet.Activities.PrivateChat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.findmypet.Activities.Post.PostModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MessageRepository {

    DatabaseReference reference;
    private MutableLiveData<List<MessageModel>> messageLiveData;
    private List<MessageModel> messageList;

    public MessageRepository(){
        messageLiveData = new MutableLiveData<>();
        messageList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public MutableLiveData<List<MessageModel>> getMessages(String currUser, String otherUser){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MessageModel messageModel = dataSnapshot.getValue(MessageModel.class);
                    messageList.add(messageModel);
                }
                messageLiveData.setValue(messageList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        // we loaded the prev messages
        Query query = reference.child("Messages")
                .child(currUser)
                .child(otherUser);
        query.addListenerForSingleValueEvent(valueEventListener);


        return messageLiveData;
    }

}
