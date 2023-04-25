package com.example.findmypet.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.findmypet.Activities.ChatsActivity;
import com.example.findmypet.Activities.MainActivity;
import com.example.findmypet.Activities.SplashActivity;
import com.example.findmypet.Adapters.HomeAdapter;
import com.example.findmypet.Models.Post;
import com.example.findmypet.Models.PostModel;
import com.example.findmypet.R;
import com.example.findmypet.Utils.OnSwipeTouchListener;
import com.example.findmypet.databinding.FragmentHomeBinding;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<PostModel> postList;
    private RecyclerView recyclerView_home;
    private HomeAdapter adapter;
    private ContentLoadingProgressBar progressBar;
    Activity activity;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

//        constraintHome.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
//            public void onSwipeTop() {
//                startActivity(new Intent(getContext(), ChatsActivity.class));
//            }
//            public void onSwipeRight() {
//
//            }
//            public void onSwipeLeft() {
//                startActivity(new Intent(getContext(), ChatsActivity.class));
//            }
//            public void onSwipeBottom() {
//
//            }
//
//        });
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        init();
        ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setIcon(R.drawable.loading);
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

//        System.out.println("evet fragment çalıştı");
        homeViewModel.getPostMutableLiveData().observe(getViewLifecycleOwner(), postModels -> {
//            System.out.println("view model a girdi");

            updateData(postModels);
            progress.dismiss();

        });
//        homeViewModel.getPostMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<PostModel>>() {
//            @Override
//            public void onChanged(List<PostModel> posts) {
//
//                // update ui
//                postList.clear();
//                postList.addAll(posts);
////                adapter.notifyDataSetChanged();
//                //todo: burası veriyi güncelleyeceğimiz yer normalde, ama şu an db ye bağlamadığımız
//                // için henüz, adapter ı burada set ediyoruz, sonradan değiştiricez, adapter setlemesi
//                // init de olucak, burda sadece update olucak
//
//
//                progress.dismiss();
//            }
//        });


        return root;
    }


    private void updateData(List<PostModel> postModels){
        postList.clear();
//        System.out.println("postModels: "+postModels.toString());
        postList.addAll(postModels);
        adapter = new HomeAdapter(postList, getActivity(), getContext());
        recyclerView_home.setAdapter(adapter);
    }

//    public FragmentManager getMyFragmentManager() {
//        return getChildFragmentManager();
//    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(){
        recyclerView_home = binding.recyclerViewHome;
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView_home.setLayoutManager(layoutManager);
        postList = new ArrayList<>();
//        postList = SplashActivity.getPosts();
        System.out.println("postlar: "+postList);


        MaterialToolbar toolbar = binding.toolbarMainFragment;
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        // swipe left to launch ChatsActivity
        recyclerView_home.setOnTouchListener(new OnSwipeTouchListener(getContext()){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                startActivity(new Intent(getContext(), ChatsActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        SwipeRefreshLayout swipeRefreshLayout = binding.swipeToRefreshHome;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //update
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        boolean isLoading = false;
        recyclerView_home.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                if (lastVisibleItemPosition == totalItemCount - 1) {
                    // End has been reached, load more data
//                    loadPosts();
                    binding.progressBarHome.show();
                    Toast.makeText(getContext(), "scrolled",Toast.LENGTH_SHORT).show();
                 /*   isLoading = true;*/
                }
            }
        });
//        recyclerView_home.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
//                Toast.makeText(getContext(), "scrolled",Toast.LENGTH_SHORT).show();
//            }
//        });
//        recyclerView_home.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                Toast.makeText(getContext(), "scrolled",Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    // call onCreateOptionsMenu
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    // set search feature
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu_home,menu);
        MenuItem searchItem = menu.findItem(R.id.search_home);
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
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.chats){
            startActivity(new Intent(getContext(), ChatsActivity.class));
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}