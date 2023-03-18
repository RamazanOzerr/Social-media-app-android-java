package com.example.findmypet.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findmypet.Adapters.NotificationAdapter;
import com.example.findmypet.Models.NotificationModel;
import com.example.findmypet.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private List<NotificationModel> notificationModelList;
    private RecyclerView recyclerView_noti;
    private NotificationAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textNotifications;
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        init();

        notificationsViewModel.getNotifications().observe(getViewLifecycleOwner(), notificationModels -> {
            // update
            notificationModelList.clear();
            notificationModelList.addAll(notificationModels);
            adapter = new NotificationAdapter(notificationModelList);
            recyclerView_noti.setAdapter(adapter);
        });

        return root;

    }

    private void init(){
        recyclerView_noti = binding.recyclerViewNoti;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView_noti.setLayoutManager(layoutManager);
        notificationModelList = new ArrayList<>();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}