package com.example.findmypet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.findmypet.R;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView signInTextView, forgetPasswordId;
    private EditText email, password;
    private Button loginButton;
    private ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    private String email_user, password_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        openSignIn();
        login();

        forgetPasswordId.setOnClickListener(view -> {
            email_user = email.getText().toString().trim();
            resetPassword(email_user);
        });
    }

    private void resetPassword(String email) {
        //TODO: reset password yazılacak
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(getApplicationContext(),
                        "password reset email been sented, please check your mail box",
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),
                        "email could not sented, please check the accuracy of your email",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        signInTextView = findViewById(R.id.signIntextView);
        forgetPasswordId = findViewById(R.id.forgetPasswordId);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        MaterialToolbar toolbar = findViewById(R.id.toolBar_login);
        setSupportActionBar(toolbar);
    }

    public void login(){
        loginButton.setOnClickListener(v -> {

            email_user = email.getText().toString().trim();
            password_user = password.getText().toString().trim();

            if (TextUtils.isEmpty(email_user)) {
                email.setError("Email is required");

            }
            if (TextUtils.isEmpty(password_user)) {
                password.setError("Password is required");
            }
            if (password.length() < 8) {
                password.setError("Password must be at least 8 characters");
            }

            progressBar.setVisibility(View.VISIBLE);
            try {
                firebaseAuth.signInWithEmailAndPassword(email_user , password_user).addOnCompleteListener(task -> {

                    if(task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this,
                                "Logged in Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else {
                        Toast.makeText(LoginActivity.this,
                                "Error!", Toast.LENGTH_LONG).show();
                    }
                });
            }

            catch (IllegalArgumentException e) {
                Toast.makeText(LoginActivity.this, "You must enter the password",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    public void openSignIn() {
        signInTextView.setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));
    }
}