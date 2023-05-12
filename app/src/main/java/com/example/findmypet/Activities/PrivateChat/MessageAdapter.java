package com.example.findmypet.Activities.PrivateChat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmypet.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<MessageModel> messageModelList;
    private String currUser;
    private Boolean state;
    int view_type_sent=1,view_type_received=2;

    public MessageAdapter(List<MessageModel> messageModelList, String currUser){
        this.messageModelList = messageModelList;
        this.currUser = currUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == view_type_sent){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sent_item,
                    parent,false);
            return new MessageAdapter.ViewHolder(view);

        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.received_item,
                    parent,false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try{
            holder.messageText.setText(messageModelList.get(position).getText());
        }catch (Exception e){
            holder.messageText.setText("");
        }
        try{
            holder.time.setText(messageModelList.get(position).getTime());
        }catch (Exception e){
            holder.time.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public int getItemViewType(int position){
        if(messageModelList.get(position).getSender().equals(currUser)){
            state = true;
            return view_type_sent;
        }else{
            state = false;
            return view_type_received;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private MaterialTextView messageText, time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if(state){
                messageText = itemView.findViewById(R.id.sendermessage);
                time = itemView.findViewById(R.id.timeofmessage);
            }else{
                messageText = itemView.findViewById(R.id.receivermessage);
                time = itemView.findViewById(R.id.timeofmessage);
            }
        }
    }
}
