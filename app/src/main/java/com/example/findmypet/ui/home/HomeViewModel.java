package com.example.findmypet.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
        Post post = new Post("ft","photo","fatih terim",
                2,5,"photo","I am the last bird fucker","online");
        Post post1 = new Post("icardi","photo","icardi",
                2,5,"photo","söylenmedi hiiiiiç","offline");
        Post post2 = new Post("diagne","photo","diagne",
                2,5,"photo","olmasaydı sonumuz böyle","online");
        Post post3 = new Post("falcao","photo","falcao",
                2,5,"photo","sakat doğdum sakat ölücem","online");

        postList.add(post);
        postList.add(post2);
        postList.add(post3);
        postList.add(post1);
        postList.add(post2);
        postList.add(post);
        postList.add(post2);
        postList.add(post3);
        postList.add(post1);
        postList.add(post2);

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