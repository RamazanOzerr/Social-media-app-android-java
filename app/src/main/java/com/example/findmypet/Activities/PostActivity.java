package com.example.findmypet.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.findmypet.Models.ProfileModel;
import com.example.findmypet.R;
import com.example.findmypet.Utils.RandomString;
import com.example.findmypet.databinding.ActivityPostBinding;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    private AppCompatImageView photoIcon, videoIcon;
    private AppCompatImageView imageArea, galleryIcon;
    private FloatingActionButton sendButton;
    private ActivityPostBinding binding;
    private TextView text;
    private VideoView videoArea;
    private MaterialToolbar toolbar;
    private MaterialTextView textView;
    private final int GALLERY = 200;
    private final int CAMERA = 100;
    private final int VIDEO = 300;
    private boolean hasText;
    private String imageUri;
    private String videoUri;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private String postId;
    String type;
    Uri selectedUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        init();
//        Slidr.attach(this);*/
    }

    private void init(){
        binding = ActivityPostBinding.inflate(getLayoutInflater());

        hasText = false;

        // initialize fields
        toolbar = findViewById(R.id.toolbar_post_activity);
        photoIcon = findViewById(R.id.image_post_photo);
        videoIcon = findViewById(R.id.image_post_video);
        galleryIcon = findViewById(R.id.image_post_gallery);
        textView = findViewById(R.id.text_post);
        imageArea = findViewById(R.id.image_post_activity);
        videoArea = findViewById(R.id.video_post_activity);
        MediaController controller = new MediaController(this);
        videoArea.setMediaController(controller);
        sendButton = findViewById(R.id.floatingbtn);
        text = findViewById(R.id.edittext_post);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        setButtonListeners();

        MaterialToolbar toolbar = findViewById(R.id.toolbar_post_activity);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_cancel1);
        toolbar.setNavigationOnClickListener(view ->
                startActivity(new Intent(PostActivity.this, MainActivity.class)));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        Slidr.attach(this);

//        postId = databaseReference.child("Posts").child(firebaseUser.getUid())
//                .push().getKey();
//        Map<String, Object> map = new HashMap<>();
//
//        if(postId != null){
//            databaseReference.child("Posts").child(firebaseUser.getUid())
//                    .child(postId).setValue(map);
//        }else{
//            System.out.println();
//            //todo: burda post id oluşana kadar denememiz lazım sanırım
//        }


    }

    private void setButtonListeners(){
        sendButton.setOnClickListener(view -> {
            post();
        });

        photoIcon.setOnClickListener(view -> {
            launchCamera();
        });

        videoIcon.setOnClickListener(view -> {
            recordVideo();
        });

        galleryIcon.setOnClickListener(view -> {
            launchGallery();
        });
    }

    private void launchCamera() {

        try{
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAMERA);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "an error accured, please try again later",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void recordVideo() {
        try{
            Intent intent = new Intent();
            intent.setType("video/*");
            intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent, VIDEO);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "an error accured, please try again later",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void launchGallery(){
        try{
            Intent intent = new Intent();
            intent.setType("image/*, video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Media"),GALLERY);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "an error accured, please try again later",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void post(){
        if(selectedUri != null){
            ProgressDialog progress = new ProgressDialog(PostActivity.this);
            progress.setTitle("posting");
            progress.setMessage("Please wait sec while posting...");
            progress.setIcon(R.drawable.loading);
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
            final StorageReference ref = FirebaseStorage.getInstance().getReference()
                    .child("Pictures")
                    .child(RandomString.getSaltString() + ".jpg");
            UploadTask uploadTask = ref.putFile(selectedUri);

            String finalType = type;
            Task<Uri> urlTask = uploadTask.continueWithTask(task -> {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    postId = databaseReference.child("Posts").child(firebaseUser.getUid())
                            .push().getKey();
                    databaseReference = databaseReference.child("Posts")
                            .child(firebaseUser.getUid()).child(postId);
                    Map<String, Object> map = new HashMap<>();
                    map.put("username", "username");
                    map.put("profile_photo", "profile photo");
                    map.put("url",downloadUri.toString());
                    map.put("type", finalType);
                    map.put("comment_number", 0);
                    map.put("like_number", 0);
                    map.put("post_time", getTime());
                    map.put("text",text.getText().toString());
                    //todo: alttaki 2 liste yorum ve like geldiğinde oluşturulacak
//                    map.put("comments", "username"); // list
//                    map.put("likes", "username"); // list


                    databaseReference.setValue(map);
                    getUserInfo();

                    progress.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "it's been successfully posted",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"nothing been chosen",
                    Toast.LENGTH_SHORT).show();
        }

    }



    // todo : open gallery when clicked photo icon


    // galeriyi açan method
    public void pickPhotoFromGallery(View view) {

        photoIcon.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i = new Intent();
             i.setType("image/*");
             i.setAction(Intent.ACTION_GET_CONTENT);

             // pass the constant to compare it
             // with the returned requestCode
             startActivityForResult(Intent.createChooser(i, "Select Picture"), GALLERY);
         }
     });

    }

    private void getUserInfo(){
        if(firebaseUser != null) {
            // get user data from db
            DatabaseReference reference = FirebaseDatabase.getInstance()
                    .getReference().child("Users")
                    .child(firebaseUser.getUid());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String username = snapshot.child("username").getValue().toString();
//                    String name = snapshot.child("name").getValue().toString();
//                    String bio = snapshot.child("bio").getValue().toString();
                    String photo = snapshot.child("photo").getValue().toString();
//                    String following_number = snapshot.child("following_number").getValue().toString();
//                    String followers_number = snapshot.child("follower_number").getValue().toString();
//                    String post_number = snapshot.child("post").getValue().toString();
                    Map<String, Object> map = new HashMap<>();
                    map.put("username", username);
                    map.put("profile_photo", photo);

                    databaseReference.updateChildren(map);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK && data != null) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == GALLERY) {
                // Get the url of the image from data
                selectedUri = data.getData();
                if (selectedUri != null) {
                    String type = getContentResolver().getType(selectedUri);
                    if(type != null){
                        textView.setVisibility(View.GONE);
                        if(type.startsWith("video/")){
                            videoArea.setVideoURI(selectedUri);
                            imageArea.setVisibility(View.GONE);
                            videoArea.setVisibility(View.VISIBLE);
                        }else{
                            imageArea.setImageURI(selectedUri);
                            imageArea.setVisibility(View.VISIBLE);
                            videoArea.setVisibility(View.GONE);
                        }
                    }

                }
            } else if (requestCode == VIDEO) {
                selectedUri = data.getData();
                type = "video";
                if(selectedUri != null){
                    videoArea.setVideoURI(selectedUri);
                    // add photo into storage and get the link and set it into db
                }
            }
//            else if (requestCode == CAMERA) {
//                Bundle extras = data.getExtras();
//                Bitmap imageBitmap = (Bitmap) extras.get("data");
//                imageArea.setImageBitmap(imageBitmap);
////                selectedUri = data.getData();
//                type = "photo";
//            }
        }
}
    private String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }


    // todo: open
}