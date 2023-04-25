package com.example.findmypet.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.findmypet.Models.CommentModel;
import com.example.findmypet.Models.PostModel;
import com.example.findmypet.Models.ViewLikesModel;
import com.example.findmypet.Repositories.PostRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<PostModel>> posts;
//    private PostRepository postRepository = PostRepository.getInstance();
    private PostRepository postRepository;
//    private List<PostModel> postList;
    DatabaseReference reference;

    public HomeViewModel() {
        super();
        posts = new MutableLiveData<>();
        postRepository = new PostRepository();
        posts = postRepository.getPosts();
//        init();
    }

    private void init(){
//        postList = new ArrayList<>();
//        populateList();
//        posts.setValue(postList);
    }

    public LiveData<List<PostModel>> getPostMutableLiveData() {
        return posts;
    }

    public LiveData<List<PostModel>> getMoreData(){

        return posts;
    }

//    private void populateList(){
//
//        reference = FirebaseDatabase.getInstance().getReference();
//        Query query = reference.child("Posts").orderByChild("post_time");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                int i = 0;
//                for(DataSnapshot snapshot1 : snapshot.getChildren()){
//                    System.out.println("veri: "+snapshot1.toString());
//                    System.out.println(snapshot1.getChildrenCount());
//                    for(DataSnapshot snapshot2 : snapshot1.getChildren()){
////                        if(i>2){
////
////                        }
////                        i++;
//                        String username = snapshot2.child("username").getValue().toString();
//                        String profile_photo = snapshot2.child("profile_photo").getValue().toString();
//                        String url = snapshot2.child("url").getValue().toString();
//                        String type = snapshot2.child("type").getValue().toString();
//                        String comment_number = snapshot2.child("comment_number").getValue().toString();
//                        String like_number = snapshot2.child("like_number").getValue().toString();
//                        String post_time = snapshot2.child("post_time").getValue().toString();
//                        String status = snapshot2.child("status").getValue().toString();
//                        String caption = snapshot2.child("text").getValue().toString();
//                        List<CommentModel> comments = new ArrayList<>();
//                        List<ViewLikesModel> likes = new ArrayList<>();
//
//                        PostModel postModel = new PostModel(username,profile_photo,url,type,
//                                comment_number,like_number,post_time,status,caption,comments,likes);
//
//
////                        postList.add(postModel);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//
//
////        Post post = new Post("ft","photo","fatih terim",
////                2,5,"photo","I am the last bird fucker","online");
////        Post post1 = new Post("icardi","photo","icardi",
////                2,5,"photo","söylenmedi hiiiiiç","offline");
////        Post post2 = new Post("diagne","photo","diagne",
////                2,5,"photo","olmasaydı sonumuz böyle","online");
////        Post post3 = new Post("falcao","photo","falcao",
////                2,5,"photo","sakat doğdum sakat ölücem","online");
////
////        postList.add(post);
////        postList.add(post2);
////        postList.add(post3);
////        postList.add(post1);
////        postList.add(post2);
////        postList.add(post);
////        postList.add(post2);
////        postList.add(post3);
////        postList.add(post1);
////        postList.add(post2);
//
//        //todo: şu an db boş olduğu için burası çalışmayacak
////        try{
////            reference.child("Posts").addValueEventListener(new ValueEventListener() {
////                @Override
////                public void onDataChange(@NonNull DataSnapshot snapshot) {
////                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
////                        Post post1 = dataSnapshot.getValue(Post.class);
////                        postList.add(post1);
////                    }
////                }
////
////                @Override
////                public void onCancelled(@NonNull DatabaseError error) {
////
////                }
////            });
////
////        }catch (Exception e){
////            System.out.println("no data");
////        }
//
//
//
//    }
}