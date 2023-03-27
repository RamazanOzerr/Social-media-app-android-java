package com.example.findmypet.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.findmypet.ActivityViewModels.ChatsViewModel;
import com.example.findmypet.Adapters.ChatsAdapter;
import com.example.findmypet.Models.ChatModel;
import com.example.findmypet.R;
import com.example.findmypet.databinding.ActivityChatsBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.List;

public class ChatsActivity extends AppCompatActivity {

    private ActivityChatsBinding binding;
    private List<ChatModel> chatModelList;
    private RecyclerView recyclerView_chats_activity;
    private ChatsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_chats);
//        Toast.makeText(ChatsActivity.this, "yes opened", Toast.LENGTH_SHORT).show();

        ChatsViewModel chatsViewModel =
                new ViewModelProvider(this).get(ChatsViewModel.class);

        init();

        chatsViewModel.getChats().observe(this, chatModels -> {
            chatModelList.clear();
            chatModelList.addAll(chatModels);
            adapter = new ChatsAdapter(chatModelList);
            recyclerView_chats_activity.setAdapter(adapter);

        });
        Slidr.attach(this);

    }


    private void init() {
        recyclerView_chats_activity = binding.recyclerViewChatsActivity;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ChatsActivity.this);
        recyclerView_chats_activity.setLayoutManager(layoutManager);
        chatModelList = new ArrayList<>();
        MaterialToolbar toolbar = findViewById(R.id.toolbar_chats_activity);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chat_activity, menu);

        MenuItem searchItem = menu.findItem(R.id.search_chats);

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
