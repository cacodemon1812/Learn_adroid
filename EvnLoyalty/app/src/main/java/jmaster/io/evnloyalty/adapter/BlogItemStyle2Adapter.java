package jmaster.io.evnloyalty.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.activity.MainActivity;
import jmaster.io.evnloyalty.fragment.blog.BlogDetailFragment;
import jmaster.io.evnloyalty.model.Post;
import jmaster.io.evnloyalty.repository.FavPostDBRepo;

public class BlogItemStyle2Adapter extends  RecyclerView.Adapter<BlogItemStyle2Adapter.ViewHolder> {
    private List<Post> posts;
    private LayoutInflater mInflater;

    public BlogItemStyle2Adapter(Activity context, List<Post> posts) {
        this.posts = posts;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_blog_item_style_2, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.txtName.setText(post.getTitle());
        holder.txtDate.setText(post.getCreatedDate());

        Glide.with(holder.itemView)
                .load("http://54.150.115.104:8080/image/" + post.getImages().get(0))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.blogImage);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtDate;
        ImageView blogImage;
        ImageView likeImage;
        ImageView shareImage;

        ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtContent);
            txtDate = itemView.findViewById(R.id.txtDate);
            blogImage = itemView.findViewById(R.id.blogImage);
            likeImage = itemView.findViewById(R.id.likeImage);
            shareImage = itemView.findViewById(R.id.shareImage);

            likeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        FavPostDBRepo repo = new FavPostDBRepo(view.getContext());
                        repo.insert(posts.get(getAdapterPosition()));
                        Toast.makeText(view.getContext(), "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        //e.printStackTrace();
                        Toast.makeText(view.getContext(), "Đã có thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            shareImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Post post = posts.get(getAdapterPosition());

                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setType("text/plain");
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, post.getTitle());
                    sendIntent.putExtra(Intent.EXTRA_TEXT, post.getTitle());
                    view.getContext().startActivity(sendIntent);
                }
            });

            txtName.setOnClickListener(new BlogDetailListener());
            blogImage.setOnClickListener(new BlogDetailListener());
        }

        private class BlogDetailListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                if (view.getContext() instanceof MainActivity) {
                    Bundle args = new Bundle();
                    args.putLong("id", posts.get(getAdapterPosition()).getId());

                    ((MainActivity) view.getContext()).setFragment(BlogDetailFragment.class, args);
                }
            }
        }
    }
}
