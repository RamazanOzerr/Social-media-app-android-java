package com.example.findmypet.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.findmypet.Activities.ChatsActivity;
import com.example.findmypet.Activities.MainActivity;
import com.example.findmypet.Adapters.HomeAdapter;
import com.example.findmypet.Models.Post;
import com.example.findmypet.R;
import com.example.findmypet.Utils.OnSwipeTouchListener;
import com.example.findmypet.databinding.FragmentHomeBinding;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<Post> postList;
    private RecyclerView recyclerView_home;
    private HomeAdapter adapter;


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
        homeViewModel.getPostMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                // update ui
                postList.clear();
                postList.addAll(posts);
//                adapter.notifyDataSetChanged();
                //todo: burası veriyi güncelleyeceğimiz yer normalde, ama şu an db ye bağlamadığımız
                // için henüz, adapter ı burada set ediyoruz, sonradan değiştiricez, adapter setlemesi
                // init de olucak, burda sadece update olucak

                adapter = new HomeAdapter(postList);
                recyclerView_home.setAdapter(adapter);
            }
        });


        return root;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(){
        recyclerView_home = binding.recyclerViewHome;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView_home.setLayoutManager(layoutManager);
        postList = new ArrayList<>();

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