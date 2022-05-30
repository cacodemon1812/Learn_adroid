package jmaster.io.evnloyalty.fragment.blog;

import android.os.Bundle;
import android.util.Log;
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
import jmaster.io.evnloyalty.adapter.BlogItemFavAdapter;
import jmaster.io.evnloyalty.adapter.BlogItemStyle1Adapter;
import jmaster.io.evnloyalty.model.Post;
import jmaster.io.evnloyalty.repository.FavPostDBRepo;
import jmaster.io.evnloyalty.service.BlogService;

public class BlogFavFragment extends Fragment {
    BlogItemFavAdapter adapter;
    List<Post> posts = new ArrayList<>();
    FavPostDBRepo favPostDBRepo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blog_fav, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList();
        initGoBack();
    }

    public void initList() {
        RecyclerView   recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        favPostDBRepo = new FavPostDBRepo(getContext());
        adapter = new BlogItemFavAdapter(getActivity(), posts);
        adapter.setItemClickListener(new BlogItemFavAdapter.OnItemClickListener() {
            @Override
            public void refresh() {
                loadData();
            }
        });
        recyclerView.setAdapter(adapter);

        loadData();
    }

    public void loadData() {
        posts.clear();
        posts.addAll(favPostDBRepo.findAll());
        adapter.notifyDataSetChanged();
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