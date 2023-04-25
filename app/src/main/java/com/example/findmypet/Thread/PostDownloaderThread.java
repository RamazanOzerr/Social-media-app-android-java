//package com.example.findmypet.Thread;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//
//import com.example.findmypet.Models.CommentModel;
//import com.example.findmypet.Models.PostModel;
//import com.example.findmypet.Models.ViewLikesModel;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PostDownloaderThread extends Thread {
//    private List<PostModel> posts;
//    private Context context;
//
//    public PostDownloaderThread(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    public void run() {
//        // Download post data from Firebase here
//        posts = downloadPostData();
//    }
//
//    private List<PostModel> downloadPostData() {
//        // Download post data from Firebase and return a list of PostModel objects
//        // You can use Firebase APIs to download the data here
//        List<PostModel> temp = new ArrayList<>();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//        Query query = reference.child("Posts").orderByChild("post_time");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                int i = 0;
//                for(DataSnapshot snapshot1 : snapshot.getChildren()){
//                    for(DataSnapshot snapshot2 : snapshot1.getChildren()){
//                        if(i<2){
//                            String username = snapshot2.child("username").getValue().toString();
//                            String profile_photo = snapshot2.child("profile_photo").getValue().toString();
//                            String url = snapshot2.child("url").getValue().toString();
//                            String type = snapshot2.child("type").getValue().toString();
//                            String comment_number = snapshot2.child("comment_number").getValue().toString();
//                            String like_number = snapshot2.child("like_number").getValue().toString();
//                            String post_time = snapshot2.child("post_time").getValue().toString();
//                            String status = snapshot2.child("status").getValue().toString();
//                            String caption = snapshot2.child("text").getValue().toString();
//                            List<CommentModel> comments = new ArrayList<>();
//                            List<ViewLikesModel> likes = new ArrayList<>();
//
//                            PostModel postModel = new PostModel(username,profile_photo,url,type,
//                                    comment_number,like_number,post_time,status,caption,comments,likes);
//
//
//                            temp.add(postModel);
//
//                            i++;
////                            System.out.println(postModel);
//                        }else{
//                            break;
//                        }
//
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                System.out.println("hata");
//            }
//        });
//
////        System.out.println("temp: "+temp);
////        System.out.println("temp dış: ");
////        for (PostModel postModel1 : temp){
////            System.out.println(postModel1);
////        }
//        return temp;
//    }
//
//    public List<PostModel> getPosts() {
//        return posts;
//    }
//}
