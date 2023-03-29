package com.example.findmypet.Adapters;

import android.graphics.pdf.PdfRenderer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmypet.Models.ChatModel;
import com.example.findmypet.Models.ViewLikesModel;
import com.example.findmypet.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewLikesAdapter extends RecyclerView.Adapter<ViewLikesAdapter.ViewHolder> implements Filterable {

    private List<ViewLikesModel> viewLikesModelList;
    private List<ViewLikesModel> viewLikesModelListFull;
    View view;
    private boolean isFollowing;

    public ViewLikesAdapter(List<ViewLikesModel> viewLikesModelList) {
        this.viewLikesModelList = viewLikesModelList;
        viewLikesModelListFull = new ArrayList<>(viewLikesModelList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewlikes_item,
                parent,false);

        return new ViewLikesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        isFollowing = false;
        try{
            holder.text_username_viewlikes.setText(viewLikesModelList.get(position).getUsername());
        }catch (Exception e){
            //
        }
        try{
            holder.text_name_viewlikes.setText(viewLikesModelList.get(position).getName());
        }catch (Exception e){
            //
        }
        try{
            holder.text_bio_viewlikes.setText(viewLikesModelList.get(position).getBio());
        }catch (Exception e){
            //
        }

        if(viewLikesModelList.get(position).getPhoto().equals("ft")){
            holder.image_viewlikes.setImageResource(R.drawable.fatih_terim);
        }else if(viewLikesModelList.get(position).getPhoto().equals("falcao")){
            holder.image_viewlikes.setImageResource(R.drawable.img);
        }
        else if(viewLikesModelList.get(position).getPhoto().equals("icardi")){
            holder.image_viewlikes.setImageResource(R.drawable.icardi);
        }
        else if(viewLikesModelList.get(position).getPhoto().equals("diagne")){
            holder.image_viewlikes.setImageResource(R.drawable.diagne);
        }

        holder.button_follow_viewlikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: set follow and unfollow feature
                if(!isFollowing){
                    Toast.makeText(view.getContext(), "user followed successfully",
                            Toast.LENGTH_SHORT).show();
                    holder.button_follow_viewlikes.setText("FOLLOWING");
                    isFollowing = true;
                }else{
                    Toast.makeText(view.getContext(), "user unfollowed successfully",
                            Toast.LENGTH_SHORT).show();
                    holder.button_follow_viewlikes.setText("FOLLOW");
                    isFollowing = false;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return viewLikesModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout constraint_viewlikes;
        private CircleImageView image_viewlikes;
        private MaterialTextView text_name_viewlikes;
        private MaterialTextView text_username_viewlikes;
        private MaterialTextView text_bio_viewlikes;
        private AppCompatButton button_follow_viewlikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraint_viewlikes = itemView.findViewById(R.id.constraint_viewlikes);
            image_viewlikes = itemView.findViewById(R.id.image_viewlikes);
            text_name_viewlikes = itemView.findViewById(R.id.text_name_viewlikes);
            text_username_viewlikes = itemView.findViewById(R.id.text_username_viewlikes);
            text_bio_viewlikes = itemView.findViewById(R.id.text_bio_viewlikes);
            button_follow_viewlikes = itemView.findViewById(R.id.button_follow_viewlikes);
        }
    }

    public Filter getFilter(){
        return chatFilter;
    }

    private Filter chatFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ViewLikesModel> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(viewLikesModelListFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(ViewLikesModel item : viewLikesModelListFull){
                    if(item.getName().toLowerCase().contains(filterPattern)){
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
            viewLikesModelList.clear();
            viewLikesModelList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
