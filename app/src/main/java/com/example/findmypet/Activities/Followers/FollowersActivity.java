package com.example.findmypet.Activities.Followers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.findmypet.Activities.MainActivity;
import com.example.findmypet.Activities.ViewPosts.ViewPostsActivity;
import com.example.findmypet.R;
import com.example.findmypet.databinding.ActivityFollowersBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.List;

public class FollowersActivity extends AppCompatActivity {

    private FollowersViewModel followersViewModel;
    private ActivityFollowersBinding binding;
    private List<FollowerModel> followerModelList;
    private RecyclerView recyclerView_followers;
    private FollowersAdapter adapter;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFollowersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init(){
        followersViewModel = new ViewModelProvider(this).get(FollowersViewModel.class);
        recyclerView_followers = binding.recyclerViewFollowersActivity;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView_followers.setLayoutManager(layoutManager);
        followerModelList = new ArrayList<>();
        activity = new ViewPostsActivity();

        MaterialToolbar toolbar = binding.toolbarFollowersActivity;
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.followers_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_cancel1);
        toolbar.setNavigationOnClickListener(view ->
                startActivity(new Intent(FollowersActivity.this, MainActivity.class)));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        // slide right to go back to the prev activity
        Slidr.attach(this);

        followersViewModel.getFollowers().observe(this, new Observer<List<FollowerModel>>() {
            @Override
            public void onChanged(List<FollowerModel> followerModels) {
                // update ui
                followerModelList.clear();
                followerModelList.addAll(followerModels);
                adapter = new FollowersAdapter(followerModelList, activity);
                recyclerView_followers.setAdapter(adapter);
            }
        });

        SwipeRefreshLayout swipeRefreshLayout_followers = binding.swipeToRefreshFollowersActivity;
        swipeRefreshLayout_followers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // update
                swipeRefreshLayout_followers.setRefreshing(false);
            }
        });

    }
}