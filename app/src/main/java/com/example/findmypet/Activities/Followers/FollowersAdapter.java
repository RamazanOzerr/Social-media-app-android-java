package com.example.findmypet.Activities.Followers;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmypet.R;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.ViewHolder> {

    private List<FollowerModel> followerModelList;
    View view;
    Activity activity;

    public FollowersAdapter(List<FollowerModel> followerModelList, Activity activity) {
        this.followerModelList = followerModelList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_item,
                parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try{
            holder.text_name_followers.setText(followerModelList.get(position).getName());
        }catch (Exception e){
            //
        }

        try{
            holder.text_bio_followers.setText(followerModelList.get(position).getName());
        }catch (Exception e){
            //
        }

        try{
            Picasso.get().load(followerModelList.get(position).getPhoto())
                    .into(holder.image_followers);
        }catch (Exception e){
            //
        }
        holder.image_followers.setImageResource(R.drawable.fatih_terim);
    }

    @Override
    public int getItemCount() {
        return followerModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView image_followers;
        private MaterialTextView text_name_followers;
        private MaterialTextView text_bio_followers;
        private Button button_follow_followers;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_followers = itemView.findViewById(R.id.image_followers);
            text_name_followers = itemView.findViewById(R.id.text_name_followers);
            text_bio_followers = itemView.findViewById(R.id.text_bio_followers);
            button_follow_followers = itemView.findViewById(R.id.button_follow_followers);

        }
    }


}
