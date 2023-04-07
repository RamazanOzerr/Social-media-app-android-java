package com.example.findmypet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.findmypet.ActivityViewModels.PostViewModel;
import com.example.findmypet.ActivityViewModels.ViewProfileViewModel;
import com.example.findmypet.Adapters.ViewPostAdapter;
import com.example.findmypet.Adapters.ViewProfilePostsAdapter;
import com.example.findmypet.Models.Post;
import com.example.findmypet.Models.ViewProfileModel;
import com.example.findmypet.Models.ViewProfilePostModel;
import com.example.findmypet.R;
import com.example.findmypet.databinding.ActivityViewProfileBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.List;

public class ViewProfileActivity extends AppCompatActivity {

    private ViewProfileViewModel profileViewModel;
    private ActivityViewProfileBinding binding;
    private List<ViewProfilePostModel> postList;
    private RecyclerView recyclerView_viewprofileactivity;
    private ViewProfilePostsAdapter adapter;
    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init(){
        profileViewModel = new ViewModelProvider(this).get(ViewProfileViewModel.class);
        recyclerView_viewprofileactivity = binding.recyclerViewViewprofileactivity;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView_viewprofileactivity.setLayoutManager(layoutManager);
        postList = new ArrayList<>();
        activity = new ViewProfileActivity();

        // set toolbar
        MaterialToolbar toolbar = binding.toolbarViewprofileactivity;
        setSupportActionBar(toolbar);
        toolbar.setTitle("POSTS");
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        // slide right to go back to the prev activity
        Slidr.attach(this);

        profileViewModel.getProfile().observe(this, new Observer<ViewProfileModel>() {
            @Override
            public void onChanged(ViewProfileModel viewProfileModel) {
                postList.clear();
                postList.addAll(viewProfileModel.getPostModelList());
                adapter = new ViewProfilePostsAdapter(postList, activity, getApplicationContext());
                recyclerView_viewprofileactivity.setAdapter(adapter);
                setInfo(viewProfileModel);
            }
        });
    }

    private void setInfo(ViewProfileModel viewProfileModel){
        binding.imageViewprofileactivity.setImageResource(R.drawable.icardi);
        binding.bioViewprofileactivity.setText(viewProfileModel.getBio());
        binding.followersViewprofileactivity.setText("Followers: "+viewProfileModel.getFollower_number());
        binding.followingsViewprofileactivity.setText("Followings: "+viewProfileModel.getFollowing_number());
        binding.postsViewprofileactivity.setText("Posts: "+viewProfileModel.getPost_number());
        binding.usernameViewprofileactivity.setText(viewProfileModel.getUsername());
        binding.nameViewprofileactivity.setText(viewProfileModel.getName());

    }
}