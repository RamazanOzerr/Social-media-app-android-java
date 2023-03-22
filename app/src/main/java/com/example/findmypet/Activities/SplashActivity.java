package com.example.findmypet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.example.findmypet.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth auth;

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
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }else{
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }


}