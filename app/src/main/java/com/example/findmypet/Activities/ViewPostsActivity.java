package com.example.findmypet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.findmypet.ActivityViewModels.CommentsViewModel;
import com.example.findmypet.ActivityViewModels.PostViewModel;
import com.example.findmypet.Adapters.CommentsAdapter;
import com.example.findmypet.Adapters.ViewPostAdapter;
import com.example.findmypet.Models.CommentModel;
import com.example.findmypet.Models.Post;
import com.example.findmypet.R;
import com.example.findmypet.databinding.ActivityCommentsBinding;
import com.example.findmypet.databinding.ActivityViewPostsBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.List;

public class ViewPostsActivity extends AppCompatActivity {

    private PostViewModel postViewModel;
    private ActivityViewPostsBinding binding;
    private List<Post> postList;
    private RecyclerView recyclerView_viewpost;
    private ViewPostAdapter adapter;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPostsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init(){
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        recyclerView_viewpost = binding.recyclerViewViewpostactivity;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView_viewpost.setLayoutManager(layoutManager);
        postList = new ArrayList<>();
        activity = new ViewPostsActivity();

        // set toolbar
        MaterialToolbar toolbar = binding.toolbarViewpostactivity;
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.viewpost_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_cancel1);
        toolbar.setNavigationOnClickListener(view ->
                startActivity(new Intent(ViewPostsActivity.this, MainActivity.class)));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        // slide right to go back to the prev activity
        Slidr.attach(this);

        postViewModel.getPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> postModels) {
                // update
                postList.clear();
                postList.addAll(postModels);
                adapter = new ViewPostAdapter(postList, activity, getApplicationContext());
                recyclerView_viewpost.setAdapter(adapter);
            }
        });

        SwipeRefreshLayout swipe_to_refresh_comments = findViewById(R.id.swipe_to_refresh_viewpostactivity);
        swipe_to_refresh_comments.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // update
                swipe_to_refresh_comments.setRefreshing(false);
            }
        });
    }
}