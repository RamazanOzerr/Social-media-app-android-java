package com.example.findmypet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.findmypet.R;
import com.example.findmypet.Utils.RandomString;
import com.example.findmypet.databinding.ActivitySignUpBinding;
import com.example.findmypet.databinding.ActivitySignUpSetProfileBinding;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpSetProfileActivity extends AppCompatActivity {

    ActivitySignUpSetProfileBinding binding;
    private CircleImageView sign_up_profile_image;
    private EditText sign_up_name, sign_up_user_name, sign_up_about_me;
    private Button sign_up_setBtn;
    StorageReference storageReference;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_set_profile);

        init();

        sign_up_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });

        sign_up_setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpUser();
            }
        });
    }

    private void init(){
        binding = ActivitySignUpSetProfileBinding.inflate(getLayoutInflater());
        sign_up_name = binding.signUpName;
        sign_up_user_name = binding.signUpUserName;
        sign_up_about_me = binding.signUpAboutMe;
        sign_up_profile_image = findViewById(R.id.sign_up_profile_image);
        sign_up_setBtn = findViewById(R.id.sign_up_setBtn);
        storageReference = FirebaseStorage.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

    }

    private void signUpUser(){

        if(auth.getUid() == null){
            Toast.makeText(getApplicationContext(),"USER NOT FOUND",Toast.LENGTH_SHORT).show();
        }else if(sign_up_name.getText().toString() == null
                || sign_up_user_name.getText().toString() == null
                || sign_up_about_me.getText().toString() == null){
            Toast.makeText(getApplicationContext(),"you gotta fill all the blanks",Toast.LENGTH_SHORT).show();
        }else{
            Map<String, Object> map = new HashMap<>();
            map.put("username",sign_up_user_name.getText().toString());
            map.put("name",sign_up_name.getText().toString());
            map.put("bio",sign_up_about_me.getText().toString());
            map.put("follower_number",0);
            map.put("following_number",0);

            reference.child("Users").child(auth.getUid()).updateChildren(map);
            //todo: get notification token
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        if(resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();

            sign_up_name = findViewById(R.id.sign_up_name);
            sign_up_user_name = findViewById(R.id.sign_up_user_name);
            sign_up_about_me = findViewById(R.id.sign_up_about_me);
            sign_up_profile_image = findViewById(R.id.sign_up_profile_image);
            final StorageReference ref = storageReference.child("Pictures")
                    .child(RandomString.getSaltString() + ".jpg");
            UploadTask uploadTask = ref.putFile(selectedImage);

            Task<Uri> urlTask = uploadTask.continueWithTask(task -> {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    Map<String, Object> map = new HashMap<>();
                    map.put("username", sign_up_user_name.getText().toString());
                    map.put("name", sign_up_name.getText().toString());
                    map.put("photo", downloadUri.toString());
                    map.put("bio", sign_up_about_me.getText().toString());
                    map.put("follower_number",0);
                    map.put("following_number",0);


                    reference.child("Users").child(auth.getUid()).setValue(map);
                    sign_up_profile_image.setImageURI(selectedImage);
                    Toast.makeText(getApplicationContext(),
                            "picture has been successfully added", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}