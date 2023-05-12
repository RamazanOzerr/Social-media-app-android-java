package com.example.findmypet.Repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.findmypet.Activities.Post.PostModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    DatabaseReference reference;
//    private static final PostRepository instance = new PostRepository();

    private int currentPage, pageSize;
    private MutableLiveData<List<PostModel>> postsLiveData;
    private List<PostModel> postList;

//    public static PostRepository getInstance(){
//        return instance;
//    }

    public PostRepository(){
        postsLiveData = new MutableLiveData<>();
        postList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference();
        currentPage = 1;
        pageSize = 3; // download 3 posts each time
    }

    public MutableLiveData<List<PostModel>> getMorePosts(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    for(DataSnapshot snapshot2 : snapshot1.getChildren()){
                        PostModel postModel = snapshot2.getValue(PostModel.class);
                        postList.add(postModel);
                    }
                }postsLiveData.setValue(postList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        Query query = reference.child("Posts").limitToFirst(5);
        query.addListenerForSingleValueEvent(valueEventListener);

        // bu tarz bi query kullanarak user a ait postları listeleyebiliriz
        Query query1 = reference.child("Posts").orderByChild("from")
                .equalTo("user_id").limitToFirst(5);

        return postsLiveData;
    }

    public MutableLiveData<List<PostModel>> getPosts(){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    PostModel postModel = snapshot1.getValue(PostModel.class);
                    //todo: debug edebilmek için yoruma aldık, şu an kendi
                    // attığın postu görünteleyebiliyorsun
//                    if(!postModel.getOwner().equals(FirebaseAuth.getInstance()
//                            .getCurrentUser().getUid())){
//                        postList.add(postModel);
//                    }
                    postList.add(postModel);
                }
                postsLiveData.setValue(postList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        Query query = reference.child("Posts").orderByChild("post_time").limitToFirst(5);
        query.addListenerForSingleValueEvent(valueEventListener);



        return postsLiveData;
    }

    public void getPost(PaginationListener listener){

    }

    public void getNextPage(PaginationListener listener) {
        currentPage++;
        getPost(listener);
    }

    public void getPreviousPage(PaginationListener listener) {
        if (currentPage > 1) {
            currentPage--;
            getPost(listener);
        } else {
            listener.onPaginationError("You are already on the first page.");
        }
    }

    public interface PaginationListener {
        void onPaginationSuccess(List<PostModel> posts);

        void onPaginationError(String errorMessage);
    }

}
