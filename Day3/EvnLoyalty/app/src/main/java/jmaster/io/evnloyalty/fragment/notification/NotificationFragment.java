package jmaster.io.evnloyalty.fragment.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.adapter.BlogItemStyle1Adapter;
import jmaster.io.evnloyalty.adapter.NotificationItemAdapter;
import jmaster.io.evnloyalty.model.Notification;
import jmaster.io.evnloyalty.model.Post;
import jmaster.io.evnloyalty.service.BlogService;
import jmaster.io.evnloyalty.service.NotificationService;

public class NotificationFragment extends Fragment {
    NotificationItemAdapter adapter;
    List<Notification> notifications = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_notification, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList();
        initGoBack();
    }

    public void initList() {
        RecyclerView recyclerView = (RecyclerView ) getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new NotificationItemAdapter(getActivity(), notifications);
        recyclerView.setAdapter(adapter);

        loadData();
    }

    public void loadData() {
        new NotificationService().find(0, 20, new NotificationService.OnDataChangeListener() {
            @Override
            public void setListPosts(List<Notification> data) {
                notifications.clear();
                notifications.addAll(data);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void initGoBack() {
        ((ImageView) getView().findViewById(R.id.imageBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

}