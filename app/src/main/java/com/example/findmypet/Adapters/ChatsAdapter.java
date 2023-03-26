package com.example.findmypet.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmypet.Models.ChatModel;
import com.example.findmypet.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder>{

    private final List<ChatModel> chatsModelList;

    View view;

    public ChatsAdapter(List<ChatModel> chatsModelList) {
        this.chatsModelList = chatsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,
                parent,false);

        return new ChatsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return chatsModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView text_body;
        private final TextView text_username;
        private final CircleImageView image_chats;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_body = itemView.findViewById(R.id.text_event_noti);
            text_username = itemView.findViewById(R.id.text_username_noti);
            image_chats = itemView.findViewById(R.id.image_chat_item);
        }
    }
}
