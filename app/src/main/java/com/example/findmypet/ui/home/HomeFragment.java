package com.example.findmypet.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmypet.Adapters.HomeAdapter;
import com.example.findmypet.Models.PhotoPost;
import com.example.findmypet.Models.Post;
import com.example.findmypet.Models.VideoPost;
import com.example.findmypet.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<Post> postList;
    private RecyclerView recyclerView_home;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);


        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        init();
        return root;
    }

    private void init(){
        recyclerView_home = binding.recyclerViewHome;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView_home.setLayoutManager(layoutManager);
        postList = new ArrayList<>();
        getPosts();
    }

    private void getPosts(){
        Post post = new Post("media","photo","username",
                2,5,"photo");
        postList.add(post);
        postList.add(post);
        postList.add(post);
        postList.add(post);
        HomeAdapter adapter = new HomeAdapter(postList);
        recyclerView_home.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}