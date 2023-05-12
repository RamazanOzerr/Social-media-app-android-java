package com.example.findmypet.Activities.Followings;

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

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewHolder>{

    private List<FollowingModel> followingModelList;
    View view;

    public FollowingAdapter(List<FollowingModel> followingModelList) {
        this.followingModelList = followingModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.following_item,
                parent,false);

        return new FollowingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try{
            holder.text_name_followings.setText(followingModelList.get(position).getName());
        }catch (Exception e){
            //
        }

        try{
            holder.text_bio_followings.setText(followingModelList.get(position).getName());
        }catch (Exception e){
            //
        }

        try{
            Picasso.get().load(followingModelList.get(position).getPhoto())
                    .into(holder.image_followings);
        }catch (Exception e){
            //
        }
        holder.image_followings.setImageResource(R.drawable.fatih_terim);
    }

    @Override
    public int getItemCount() {
        return followingModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView image_followings;
        private MaterialTextView text_name_followings;
        private MaterialTextView text_bio_followings;
        private Button button_remove_followings;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_followings = itemView.findViewById(R.id.image_followings);
            text_name_followings = itemView.findViewById(R.id.text_name_followings);
            text_bio_followings = itemView.findViewById(R.id.text_bio_followings);
            button_remove_followings = itemView.findViewById(R.id.button_remove_followings);
        }
    }
}
