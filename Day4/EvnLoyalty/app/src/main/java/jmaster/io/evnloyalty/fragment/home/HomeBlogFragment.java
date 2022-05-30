package jmaster.io.evnloyalty.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.adapter.BlogItemStyle2Adapter;
import jmaster.io.evnloyalty.model.Post;
import jmaster.io.evnloyalty.service.BlogService;

public class HomeBlogFragment extends Fragment {
    BlogItemStyle2Adapter adapter;
    List<Post> posts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_blog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        adapter = new BlogItemStyle2Adapter(getActivity(), posts);
        recyclerView.setAdapter(adapter);

        loadPosts();//load du lieu
    }

    public void loadPosts() {
        new BlogService().find(0, 3, new BlogService.OnDataChangeListener() {
            @Override
            public void setListPosts(List<Post> data) {
                posts.clear();
                posts.addAll(data);
                adapter.notifyDataSetChanged();
            }
        });
    }
}