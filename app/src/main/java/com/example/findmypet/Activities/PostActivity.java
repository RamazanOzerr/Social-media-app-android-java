package com.example.findmypet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findmypet.R;
import com.example.findmypet.databinding.ActivityPostBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;

public class PostActivity extends AppCompatActivity {

    ImageView photoIcon, videoIcon;
    AppCompatImageView imageArea;
    FloatingActionButton sendButton;
    TextView text;
    int SELECT_PICTURE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // initialize fields
        photoIcon = findViewById(R.id.image_post_photo);
        videoIcon = findViewById(R.id.image_post_video);
        imageArea = findViewById(R.id.image_post_activity);
        sendButton = findViewById(R.id.floatingbtn);
        text = findViewById(R.id.edittext_post);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_post_activity);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_cancel1);
        toolbar.setNavigationOnClickListener(view ->
                startActivity(new Intent(PostActivity.this, MainActivity.class)));

        Slidr.attach(this);
//        Slidr.attach(this);*/
    }

    // todo : open gallery when clicked photo icon

    public void pickPhotoFromGallery(View view) {

        photoIcon.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i = new Intent();
             i.setType("image/*");
             i.setAction(Intent.ACTION_GET_CONTENT);

             // pass the constant to compare it
             // with the returned requestCode
             startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
         }
     });

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageArea.setImageURI(selectedImageUri);
                }
            }
    }
}


    // todo: open
}