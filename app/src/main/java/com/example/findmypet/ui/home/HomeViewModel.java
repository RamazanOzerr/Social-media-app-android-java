package com.example.findmypet.ui.home;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.findmypet.Models.PhotoPost;
import com.example.findmypet.Models.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<Post>> posts;
    private List<Post> postList;
    DatabaseReference reference;

    public HomeViewModel() {
        posts = new MutableLiveData<>();
        init();
    }

    private void init(){
        postList = new ArrayList<>();
        populateList();
        posts.setValue(postList);
    }

    public LiveData<List<Post>> getPostMutableLiveData() {
        return posts;
    }

    private void populateList(){
        Post post = new Post("media","photo","username",
                2,5,"photo");

        postList.add(post);
        postList.add(post);
        postList.add(post);
        postList.add(post);
        postList.add(post);


        //todo: şu an db boş olduğu için burası çalışmayacak
        try{
            reference.child("Posts").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Post post1 = dataSnapshot.getValue(Post.class);
                        postList.add(post1);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }catch (Exception e){
            System.out.println("no data");
        }



    }
}