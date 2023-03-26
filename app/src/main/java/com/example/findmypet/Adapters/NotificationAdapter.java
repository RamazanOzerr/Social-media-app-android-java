package com.example.findmypet.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmypet.Models.NotificationModel;
import com.example.findmypet.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{

    private final List<NotificationModel> notificationModelList;
    View view;

    public NotificationAdapter(List<NotificationModel> notificationModelList) {
        this.notificationModelList = notificationModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,
                parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try{
            holder.text_body.setText(notificationModelList.get(position).getBody());
        }catch (Exception e){
            holder.text_body.setText("");
        }

        try{
            holder.text_username.setText(notificationModelList.get(position).getName());
        }catch (Exception e){
            holder.text_username.setText("");
        }

        try{
            //todo: debug için şimdilik fotoyu kendimiz set ediyoruz, db devreye girince yorumdan
            // çıkarcaz methodu
//            Picasso.get().load(notificationModelList.get(position)
//                    .getPhoto()).into(holder.image_noti);
            holder.image_noti.setImageResource(R.mipmap.ic_account);
        }catch (Exception e){
            // it'll set the default photo, so it'll remain as it is setted in xml file
            holder.image_noti.setImageResource(R.mipmap.ic_account);
        }


    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView text_body;
        private final TextView text_username;
        private final ImageView image_noti;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_body = itemView.findViewById(R.id.text_event_noti);
            text_username = itemView.findViewById(R.id.text_username_noti);
            image_noti = itemView.findViewById(R.id.image_noti);
        }
    }
}
