package jmaster.io.evnloyalty.fragment.blog;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.adapter.BlogItemStyle2Adapter;
import jmaster.io.evnloyalty.model.Post;
import jmaster.io.evnloyalty.repository.FavPostDBRepo;
import jmaster.io.evnloyalty.service.BlogService;

public class BlogDetailFragment extends Fragment {
    Post post = new Post();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blog_detail, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initGoBack();
        initData();
        initShare();
        initLike();
    }

    public void initData() {
        Bundle bundle = getArguments();
        Long id = bundle.getLong("id");

        new BlogService().getOne(id, new BlogService.OnDataChangeListener() {
            @Override
            public void setPost(Post data) {
                post = data;
                ((TextView) getView().findViewById(R.id.txtTitle)).setText(post.getTitle());
                ((TextView) getView().findViewById(R.id.txtDate)).setText(post.getCreatedDate());

                Glide.with(getContext())
                        .load("http://54.150.115.104:8080/image/" + post.getImages().get(0))
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(((ImageView) getView().findViewById(R.id.blogImage)));


                TextView txtDescription = ((TextView) getView().findViewById(R.id.txtDescription));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    txtDescription.setText(Html.fromHtml(post.getDescription(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    txtDescription.setText(Html.fromHtml(post.getDescription()));
                }
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

    public void initShare() {
        getView().findViewById(R.id.shareImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, post.getTitle());
                sendIntent.putExtra(Intent.EXTRA_TEXT, post.getTitle());
                view.getContext().startActivity(sendIntent);
            }
        });
    }

    public  void initLike() {
        getView().findViewById(R.id.likeImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///luu du lieu bai viet uu thich xuong database
                try {
                    FavPostDBRepo repo = new FavPostDBRepo(view.getContext());
                    repo.insert(post);
                    Toast.makeText(view.getContext(), "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    //e.printStackTrace();
                    Toast.makeText(view.getContext(), "Đã có thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}