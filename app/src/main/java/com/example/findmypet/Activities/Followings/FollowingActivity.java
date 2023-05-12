package com.example.findmypet.Activities.Followings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;

import com.example.findmypet.Activities.MainActivity;
import com.example.findmypet.R;
import com.example.findmypet.databinding.ActivityFollowingBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.List;

public class FollowingActivity extends AppCompatActivity {

    private FollowingViewModel followingViewModel;
    private ActivityFollowingBinding binding;
    private List<FollowingModel> followingModelList;
    private RecyclerView recyclerView_followings;
    private FollowingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFollowingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

    }
    private void init(){
        followingViewModel = new ViewModelProvider(this).get(FollowingViewModel.class);
        recyclerView_followings = binding.recyclerViewFollowingActivity;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView_followings.setLayoutManager(layoutManager);
        followingModelList = new ArrayList<>();

        MaterialToolbar toolbar = binding.toolbarFollowingActivity;
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_cancel1);
        toolbar.setNavigationOnClickListener(view ->
                startActivity(new Intent(FollowingActivity.this, MainActivity.class)));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        // slide right to go back to the prev activity
        Slidr.attach(this);

        followingViewModel.getFollowings().observe(this, new Observer<List<FollowingModel>>() {
            @Override
            public void onChanged(List<FollowingModel> followingModels) {
                // update ui
                followingModelList.clear();
                followingModelList.addAll(followingModels);
                adapter = new FollowingAdapter(followingModelList);
                recyclerView_followings.setAdapter(adapter);
            }
        });


        SwipeRefreshLayout swipeRefreshLayout_following = binding.swipeToRefreshFollowingActivity;
        swipeRefreshLayout_following.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // update
                swipeRefreshLayout_following.setRefreshing(false);
            }
        });

    }
}