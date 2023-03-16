package com.example.findmypet.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmypet.Models.PhotoPost;
import com.example.findmypet.Models.Post;
import com.example.findmypet.Models.VideoPost;
import com.example.findmypet.R;
import com.example.findmypet.ViewHolders.ViewHolderHome;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<ViewHolderHome> {

    private List<Post> postList;
    int view_type_photo = 1, view_type_video = 2, view_type_text;

    public HomeAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if(viewType == view_type_photo){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_post_item,
                    parent,false);
            return new ViewHolderHome(view);

        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_post_item,
                    parent,false);
            return new ViewHolderHome(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHome holder, int position) {

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

//    public int getItemViewType(int position){
//        Object post = postList.get(position);
//

//        if(messageModelList.get(position).getFrom().equals(userId)){
//            state = true;
//            return view_type_photo;
//        }else{
//            state = false;
//            return view_type_video;
//        }
//    }
    public int getItemViewType(int position){
//        return 1;
        if(postList.get(position).getType().equals("photo")){
            return view_type_photo;
        }else{
            return view_type_video;
        }
    }
}
