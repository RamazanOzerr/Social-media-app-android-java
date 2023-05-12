package com.example.findmypet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.findmypet.Activities.Post.PostModel;
import com.example.findmypet.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth auth;
    private static List<PostModel> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        auth = FirebaseAuth.getInstance();
        ImageView imageView = findViewById(R.id.image_splash);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_anim);
        imageView.startAnimation(animation);

        new Handler().postDelayed(() -> {
            startActivity();
            finish();
        },500);
    }

    private void startActivity(){
        if(auth.getCurrentUser() != null){
//            PostDownloaderThread downloaderThread = new PostDownloaderThread(this);
//            downloaderThread.start();
//
//            // Wait for the thread to finish and retrieve the downloaded post data
//            try {
//                downloaderThread.join();
//                posts = downloaderThread.getPosts();
//                System.out.println("posto: "+posts);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            posts = downloadPosts();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }else{
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }

//    private List<PostModel> downloadPosts(){
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
//                            System.out.println("temp: ");
//                            for (PostModel postModel1 : temp){
//                                System.out.println("mal: ");
//                                System.out.println(postModel1.toString());
//                            }
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
//        return temp;
//    }
    public static List<PostModel> getPosts() {

        return posts;
    }


}