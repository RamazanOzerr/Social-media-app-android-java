package com.example.findmypet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.findmypet.ActivityViewModels.CommentsViewModel;
import com.example.findmypet.ActivityViewModels.ViewLikesViewModel;
import com.example.findmypet.Adapters.CommentsAdapter;
import com.example.findmypet.Adapters.ViewLikesAdapter;
import com.example.findmypet.Models.CommentModel;
import com.example.findmypet.Models.ViewLikesModel;
import com.example.findmypet.R;
import com.example.findmypet.databinding.ActivityCommentsBinding;
import com.example.findmypet.databinding.ActivityViewLikesBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    private CommentsViewModel commentsViewModel;
    private ActivityCommentsBinding binding;
    private List<CommentModel> commentModelList;
    private RecyclerView recyclerView_comments;
    private CommentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

    }

    private void init(){
        commentsViewModel = new ViewModelProvider(this).get(CommentsViewModel.class);
        recyclerView_comments = binding.recyclerViewComments;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_comments.setLayoutManager(layoutManager);
        commentModelList = new ArrayList<>();

        // set toolbar
        MaterialToolbar toolbar = binding.toolbarCommentActivity;
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_cancel1);
        toolbar.setNavigationOnClickListener(view ->
                startActivity(new Intent(CommentsActivity.this, MainActivity.class)));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        // slide right to go back to the prev activity
        Slidr.attach(this);

        commentsViewModel.getComments().observe(this, new Observer<List<CommentModel>>() {
            @Override
            public void onChanged(List<CommentModel> commentModels) {
                // update
                commentModelList.clear();
                commentModelList.addAll(commentModels);
                adapter = new CommentsAdapter(commentModelList);
                recyclerView_comments.setAdapter(adapter);
            }
        });

        SwipeRefreshLayout swipe_to_refresh_comments = findViewById(R.id.swipe_to_refresh_comments);
        swipe_to_refresh_comments.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // update
                swipe_to_refresh_comments.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_comment_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_comments);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}