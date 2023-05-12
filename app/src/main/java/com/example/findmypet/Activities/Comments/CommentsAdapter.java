package com.example.findmypet.Activities.Comments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmypet.R;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> implements Filterable {

    private List<CommentModel> commentModelList;
    private List<CommentModel> commentModelListFull;
    View view;

    public CommentsAdapter(List<CommentModel> commentModelList) {
        this.commentModelList = commentModelList;
        commentModelListFull = new ArrayList<>(commentModelList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,
                parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            holder.text_name_comment_item.setText(commentModelList.get(position).getUsername());
        }catch (Exception e){
            //
        }
        try {
            holder.text_name_comment_item.setText(commentModelList.get(position).getUsername());
        }catch (Exception e){
            //
        }
        try {
            Picasso.get().load(commentModelList.get(position).getPhoto()).into(holder.image_comment_item);
        }catch (Exception e){
            //
        }

        // debug için, db bağlanınca silcez
//        if(commentModelList.get(position).getPhoto().equals("ft")){
//            holder.image_comment_item.setImageResource(R.drawable.icardi);
//        }

    }

    @Override
    public int getItemCount() {
        return commentModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout constraint_comment_item;
        private CircleImageView image_comment_item;
        private MaterialTextView text_name_comment_item;
        private MaterialTextView text_bio_comment_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraint_comment_item = itemView.findViewById(R.id.constraint_comment_item);
            image_comment_item = itemView.findViewById(R.id.image_comment_item);
            text_name_comment_item = itemView.findViewById(R.id.text_name_comment_item);
            text_bio_comment_item = itemView.findViewById(R.id.text_bio_comment_item);
        }
    }

    public Filter getFilter(){
        return commentFilter;
    }

    private Filter commentFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<CommentModel> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(commentModelListFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(CommentModel item : commentModelListFull){
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
            commentModelList.clear();
            commentModelList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
