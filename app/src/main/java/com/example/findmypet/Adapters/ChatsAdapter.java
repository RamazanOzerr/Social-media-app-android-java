package com.example.findmypet.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmypet.Models.ChatModel;
import com.example.findmypet.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> implements Filterable {

    private final List<ChatModel> chatsModelList;
    private List<ChatModel> chatModelListFull;

    View view;

    public ChatsAdapter(List<ChatModel> chatsModelList) {
        this.chatsModelList = chatsModelList;
        chatModelListFull = new ArrayList<>(chatsModelList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,
                parent,false);

        return new ChatsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try{
            holder.text_chats.setText(chatsModelList.get(position).getText());
        }catch (Exception e){
            holder.text_chats.setText("");
        }

        try{
            holder.text_username_chat_item.setText(chatsModelList.get(position).getName());
        }catch (Exception e){
            holder.text_username_chat_item.setText("");
        }

        if(chatsModelList.get(position).getStatus().equals("online")){
            holder.image_chat_item.setBorderColor(Color.GREEN);
        }else {
            holder.image_chat_item.setBorderColor(Color.RED);
        }

        try{
            //todo: debug için şimdilik fotoyu kendimiz set ediyoruz, db devreye girince yorumdan
            // çıkarcaz methodu
//            Picasso.get().load(notificationModelList.get(position)
//                    .getPhoto()).into(holder.image_noti);
            holder.image_chat_item.setImageResource(R.drawable.ic_account);

        }catch (Exception e){
            // it'll set the default photo, so it'll remain as it is setted in xml file
            holder.image_chat_item.setImageResource(R.drawable.ic_account);
        }

    }

    @Override
    public int getItemCount() {
        return chatsModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final MaterialTextView text_chats;
        private final MaterialTextView text_username_chat_item;
        private final CircleImageView image_chat_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_chats = itemView.findViewById(R.id.text_chats);
            text_username_chat_item = itemView.findViewById(R.id.text_username_chat_item);
            image_chat_item = itemView.findViewById(R.id.image_chat_item);
        }
    }

    public Filter getFilter(){
        return chatFilter;
    }

    private Filter chatFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ChatModel> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(chatModelListFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(ChatModel item : chatModelListFull){
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
            chatsModelList.clear();
            chatsModelList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
