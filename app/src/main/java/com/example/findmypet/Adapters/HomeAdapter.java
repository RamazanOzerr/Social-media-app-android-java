package com.example.findmypet.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.findmypet.Activities.Comments.CommentsActivity;
import com.example.findmypet.Activities.ViewLikes.ViewLikesActivity;
import com.example.findmypet.Activities.ViewProfile.ViewProfileActivity;
import com.example.findmypet.Activities.Post.PostModel;
import com.example.findmypet.R;
import com.google.android.material.textview.MaterialTextView;
import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> implements Filterable {

    private List<PostModel> postList;
    private List<PostModel> postListFull;
    private final int view_type_photo = 1, view_type_video = 2;
    private static int viewType;
    private Boolean isLiked = false;
    AnimatedVectorDrawableCompat avd;
    AnimatedVectorDrawable avd2;
    private Activity activity;
    private Context context;

    public HomeAdapter(List<PostModel> postList, Activity activity, Context context) {
        this.postList = postList;
        postListFull = new ArrayList<>(postList);
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if(viewType == view_type_photo){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_post_item,
                    parent,false);
            return new ViewHolder(view);

        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_post_item,
                    parent,false);
            return new ViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        try{
            holder.text_username.setText(postList.get(position).getUsername());
        }catch (Exception e){
            // get it from xml
        }
        try{
//            String like_num = String.valueOf(postList.get(position).getLike_number());
            holder.text_likes.setText("liked by "+postList.get(position).getLike_number()+" people");
        }catch (Exception e){
            // get it from xml
        }
        try{
//            String comments_num = String.valueOf(postList.get(position).getComment_number());
            holder.text_comments.setText("view all "+postList.get(position).getComment_number()+" comments");
        }catch (Exception e){
            // get it from xml
        }
        try{
            holder.text_caption.setText(postList.get(position).getText());
        }catch (Exception e){
            // get it from xml
        }
        try{
            Picasso.get().load(postList.get(position).getProfile_photo()).into(holder.image_profile);
        }catch (Exception e){
            holder.image_profile.setImageResource(R.drawable.ic_navi_profile);
        }



        // double click on the post to like it
        final Drawable drawable = holder.image_heart.getDrawable();
        if(postList.get(position).getType().equals("photo")){ // if post is a photo
            try{
                Picasso.get().load(postList.get(position).getUrl()).into(holder.image_post);
//                Glide.with(context).downloadOnly().load(postList.get(position).getUrl())
//                        .into(new CustomTarget<File>() {
//                            @Override
//                            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
//                                Glide.with(context).load(resource).into(holder.image_post);
//                            }
//
//                            @Override
//                            public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                            }
//                        });
            }catch (Exception e){
                holder.image_post.setImageResource(R.drawable.ic_navi_profile);
            }

            holder.image_post.setOnClickListener(new DoubleClick(new DoubleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    holder.image_like.setImageResource(R.drawable.heart);
                    isLiked = false;
                    String like_num = postList.get(position).getLike_number();
                    holder.text_likes.setText("liked by "+like_num+" people");
                }

                @Override
                public void onDoubleClick(View view) {
                    holder.image_heart.setAlpha(0.70f);
                    if(drawable instanceof AnimatedVectorDrawableCompat){
                        avd = (AnimatedVectorDrawableCompat) drawable;
                        avd.start();
                        isLiked = true;
                    }else if(drawable instanceof AnimatedVectorDrawable){
                        avd2 = (AnimatedVectorDrawable) drawable;
                        avd2.start();
                        isLiked = true;
                    }
                    holder.image_like.setImageResource(R.drawable.heart_filled);
                    String like_num = postList.get(position).getLike_number();
                    holder.text_likes.setText("liked by "+like_num+" people");
                }
            }));
        }else{ // if post is a video
            MediaController controller = new MediaController(holder.layout_middle.getContext());
            controller.setAnchorView(holder.layout_middle);
            holder.video_post.setMediaController(controller);
            try{
//                Picasso.get().load(postList.get(position).getUrl()).into(holder.video_post);
                Glide.with(context).downloadOnly().load(postList.get(position).getUrl()).into(new CustomTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        holder.video_post.setVideoPath(resource.getPath());
//                        holder.video_post.start();
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
            }catch (Exception e){
                //todo: we can display a text here, like it couldn been downloaded
            }

            // like animation when clicked on video
            holder.video_post.setOnClickListener(new DoubleClick(new DoubleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    holder.image_like.setImageResource(R.drawable.heart);
                    isLiked = false;
                    String like_num = postList.get(position).getLike_number();
                    holder.text_likes.setText("liked by "+like_num+" people");
                }

                @Override
                public void onDoubleClick(View view) {
                    holder.image_heart.setAlpha(0.70f);
                    if(drawable instanceof AnimatedVectorDrawableCompat){
                        avd = (AnimatedVectorDrawableCompat) drawable;
                        avd.start();
                        isLiked = true;
                    }else if(drawable instanceof AnimatedVectorDrawable){
                        avd2 = (AnimatedVectorDrawable) drawable;
                        avd2.start();
                        isLiked = true;
                    }
                    holder.image_like.setImageResource(R.drawable.heart_filled);
                    String like_num = postList.get(position).getLike_number();
                    holder.text_likes.setText("liked by "+like_num+" people");
                }
            }));
        }


//        try{
//
//        }catch (Exception e){
//            // get it from xml
//        }

        // written for debug
        if(postList.get(position).getStatus().equals("online")){
            holder.image_profile.setBorderColor(Color.GREEN);
        }else{
            holder.image_profile.setBorderColor(Color.RED);
        }

//        if(postList.get(position).getUrl().equals("ft")){
//            holder.image_post.setImageResource(R.drawable.fatih_terim);
//        }else if(postList.get(position).getPost_media().equals("falcao")){
//            holder.image_post.setImageResource(R.drawable.img);
//        }
//        else if(postList.get(position).getPost_media().equals("icardi")){
//            holder.image_post.setImageResource(R.drawable.icardi);
//        }
//        else if(postList.get(position).getPost_media().equals("diagne")){
//            holder.image_post.setImageResource(R.drawable.diagne);
//        }


        // set like or undo it by pressing like button
        holder.image_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isLiked){
                    holder.image_like.setImageResource(R.drawable.heart_filled);
                    isLiked = true;
                }else{
                    holder.image_like.setImageResource(R.drawable.heart);
                    isLiked = false;
                    String like_num = postList.get(position).getLike_number();
                    holder.text_likes.setText("liked by "+like_num+" people");
                }
            }
        });

        // click on likes text to view all people who likes the post
        holder.text_likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(context, ViewLikesActivity.class));
            }
        });

        holder.text_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(context, CommentsActivity.class));
            }
        });

        holder.layout_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(context, ViewProfileActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public int getItemViewType(int position){
//        return 1;
        if(postList.get(position).getType().equals("photo")){
            viewType = view_type_photo;
            return view_type_photo;
        }else{
            viewType = view_type_video;
            return view_type_video;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout layout_top; // buraya tıklandığında user profile açıcaz
        private CircleImageView image_profile;
        private MaterialTextView text_username;
        private AppCompatImageView image_like;
        private AppCompatImageView image_comment;
        private AppCompatImageView image_save;
        private MaterialTextView text_likes;
        private MaterialTextView text_comments;
        private MaterialTextView text_caption;
        private AppCompatImageView image_heart;
        private AppCompatImageView image_post; // for image posts
        private VideoView video_post; // for video posts
        private MediaController mediaController;
        private FrameLayout layout_middle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // photo post
            if(viewType == 1){
                layout_top = itemView.findViewById(R.id.layout_top);
                image_profile = itemView.findViewById(R.id.image_profile);
                text_username = itemView.findViewById(R.id.text_username);
                image_like = itemView.findViewById(R.id.image_like);
                image_comment = itemView.findViewById(R.id.image_comment);
                image_save = itemView.findViewById(R.id.image_save);
                text_likes = itemView.findViewById(R.id.text_likes);
                text_comments = itemView.findViewById(R.id.text_comments);
                text_caption = itemView.findViewById(R.id.text_caption);
                image_heart = itemView.findViewById(R.id.image_like_anim);

                image_post = itemView.findViewById(R.id.image_post);

            }else{ // video post
                layout_top = itemView.findViewById(R.id.layout_top);
                image_profile = itemView.findViewById(R.id.image_profile);
                text_username = itemView.findViewById(R.id.text_username);
                image_like = itemView.findViewById(R.id.image_like);
                image_comment = itemView.findViewById(R.id.image_comment);
                image_save = itemView.findViewById(R.id.image_save);
                text_likes = itemView.findViewById(R.id.text_likes);
                text_comments = itemView.findViewById(R.id.text_comments);
                text_caption = itemView.findViewById(R.id.text_caption);
                image_heart = itemView.findViewById(R.id.image_like_anim);

                video_post = itemView.findViewById(R.id.video_post);
                mediaController = itemView.findViewById(R.id.mediaController);
                layout_middle = itemView.findViewById(R.id.layout_middle);

            }
        }
    }
    public Filter getFilter(){
        return postFilter;
    }

    private Filter postFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<PostModel> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(postListFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(PostModel item : postListFull){
                    if(item.getUsername().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            postList.clear();
            postList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
