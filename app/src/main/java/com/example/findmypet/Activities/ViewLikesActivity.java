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

import com.example.findmypet.ActivityViewModels.ViewLikesViewModel;
import com.example.findmypet.Adapters.ViewLikesAdapter;
import com.example.findmypet.Models.ViewLikesModel;
import com.example.findmypet.R;
import com.example.findmypet.databinding.ActivityViewLikesBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.List;

public class ViewLikesActivity extends AppCompatActivity {

    private ViewLikesViewModel likesViewModel;
    private ActivityViewLikesBinding binding;
    private List<ViewLikesModel> likeList;
    private RecyclerView recyclerView_viewlikes;
    private ViewLikesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewLikesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }
    private void init(){
        likesViewModel = new ViewModelProvider(this).get(ViewLikesViewModel.class);
        recyclerView_viewlikes = binding.recyclerViewViewlikes;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_viewlikes.setLayoutManager(layoutManager);
        likeList = new ArrayList<>();

        // set toolbar
        MaterialToolbar toolbar = binding.toolbarViewlikesFragment;
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_cancel1);
        toolbar.setNavigationOnClickListener(view ->
                startActivity(new Intent(ViewLikesActivity.this, MainActivity.class)));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        // set live data
        likesViewModel.getLikes().observe(this, new Observer<List<ViewLikesModel>>() {
            @Override
            public void onChanged(List<ViewLikesModel> viewLikesModels) {
                // update
                likeList.clear();
                likeList.addAll(viewLikesModels);
                adapter = new ViewLikesAdapter(likeList);
                recyclerView_viewlikes.setAdapter(adapter);
            }
        });

        // slide right to go back to the prev activity
        Slidr.attach(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_likes_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_viewlikes);

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