package jmaster.io.evnloyalty.fragment.blog;

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

import java.util.List;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.adapter.BlogItemStyle1Adapter;
import jmaster.io.evnloyalty.adapter.BlogItemStyle2Adapter;
import jmaster.io.evnloyalty.model.Post;
import jmaster.io.evnloyalty.service.BlogService;

public class BlogFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_blog, container, false);
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

        List<Post> posts = new BlogService().findPosts();
        BlogItemStyle1Adapter adapter = new BlogItemStyle1Adapter(getActivity(), posts);
        recyclerView.setAdapter(adapter);
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