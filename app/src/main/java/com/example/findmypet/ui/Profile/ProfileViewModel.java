package com.example.findmypet.ui.Profile;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.findmypet.Models.ProfileModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private final MutableLiveData<ProfileModel> liveData;

    public ProfileViewModel(){
        liveData = new MutableLiveData<>();
        setData();
    }

    public MutableLiveData<ProfileModel> getProfileInfo(){
        setData();
        return liveData;
    }

    private void setData(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null){
            // get user data from db
            DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                    .getReference().child("Users")
                    .child(firebaseUser.getUid());
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String username = snapshot.child("username").getValue().toString();
                    String name = snapshot.child("name").getValue().toString();
                    String bio = snapshot.child("bio").getValue().toString();
                    String photo = snapshot.child("photo").getValue().toString();
                    String following_number = snapshot.child("following_number").getValue().toString();
                    String followers_number = snapshot.child("follower_number").getValue().toString();
                    String post_number = snapshot.child("post").getValue().toString();
                    liveData.setValue(new ProfileModel(username,name,photo,bio,
                            Integer.parseInt(followers_number),Integer.parseInt(following_number)
                            ,Integer.parseInt(post_number)));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }

}