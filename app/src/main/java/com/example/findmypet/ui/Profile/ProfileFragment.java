package com.example.findmypet.ui.Profile;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.findmypet.Activities.ChatsActivity;
import com.example.findmypet.Activities.FollowersActivity;
import com.example.findmypet.Activities.FollowingActivity;
import com.example.findmypet.Activities.ViewPostsActivity;
import com.example.findmypet.Adapters.HomeAdapter;
import com.example.findmypet.Models.Post;
import com.example.findmypet.Models.ProfileModel;
import com.example.findmypet.R;
import com.example.findmypet.Utils.OnSwipeTouchListener;
import com.example.findmypet.databinding.FragmentHomeBinding;
import com.example.findmypet.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        ProgressDialog progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setIcon(R.drawable.loading);
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        profileViewModel.getProfileInfo().observe(getViewLifecycleOwner(), new Observer<ProfileModel>() {
            @Override
            public void onChanged(ProfileModel profileModel) {
                setInfo(profileModel);
                progress.dismiss();
            }
        });


        setButtons();
        viewPosts();


        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void viewPosts(){
        RelativeLayout relativeLayout = binding.relativeFragmenfProfile;
        relativeLayout.setOnTouchListener(new OnSwipeTouchListener(getContext()){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                startActivity(new Intent(getContext(), ViewPostsActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    private void setButtons() {
        binding.forgetPassword.setOnClickListener(view -> {
            Toast.makeText(getContext(),"forget password",Toast.LENGTH_SHORT).show();
        });

        binding.deleteAccount.setOnClickListener(view -> {
            Toast.makeText(getContext(),"delete account",Toast.LENGTH_SHORT).show();
        });

        binding.signOut.setOnClickListener(view -> {
            Toast.makeText(getContext(),"sign out",Toast.LENGTH_SHORT).show();
        });

        binding.posts.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), ViewPostsActivity.class));
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        binding.followers.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), FollowersActivity.class));
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        binding.followings.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), FollowingActivity.class));
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    private void setInfo(ProfileModel profileModel){
        System.out.println("profil"+profileModel);
        String followers = "Followers: " + profileModel.getFollower_number();
        String followings = "Followings: " + profileModel.getFollowing_number();
        String posts = "Posts: " +  profileModel.getPost_number();

        // set profile photo
        try{
            Picasso.get().load(profileModel.getPhoto()).into(binding.userImage);
        }catch (Exception e){
            binding.userImage.setImageResource(R.drawable.ic_account);
        }

        binding.username.setText(profileModel.getUsername());
        binding.name.setText(profileModel.getName());
        binding.bio.setText(profileModel.getBio());
        binding.followers.setText(followers);
        binding.followings.setText(followings);
        binding.posts.setText(posts);
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
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.settings_home){

        }else if(item.getItemId() == R.id.posts_home){
            startActivity(new Intent(getContext(), ViewPostsActivity.class));
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }else{ // saved_home
            // view saved posts

        }

        return super.onOptionsItemSelected(item);
    }

}