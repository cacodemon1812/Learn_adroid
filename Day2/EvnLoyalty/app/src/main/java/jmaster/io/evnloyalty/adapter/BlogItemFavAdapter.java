package jmaster.io.evnloyalty.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.activity.MainActivity;
import jmaster.io.evnloyalty.fragment.blog.BlogDetailFragment;
import jmaster.io.evnloyalty.model.Post;
import jmaster.io.evnloyalty.repository.FavPostDBRepo;

public class BlogItemFavAdapter extends RecyclerView.Adapter<BlogItemFavAdapter.ViewHolder> {
    private List<Post> posts;
    private LayoutInflater mInflater;
    private OnItemClickListener itemClickListener;

    public BlogItemFavAdapter(Activity context, List<Post> posts) {
        this.posts = posts;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_blog_item_fav, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.txtName.setText(post.getTitle());

        Glide.with(holder.itemView)
                .load(post.getImages().get(0))
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
        ImageView blogImage;
        LinearLayoutCompat deleteImage;

        ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtContent);
            blogImage = itemView.findViewById(R.id.blogImage);
            deleteImage = itemView.findViewById(R.id.deleteLayout);

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        ///luu du lieu bai viet uu thich xuong database
                        FavPostDBRepo repo = new FavPostDBRepo(view.getContext());
                        repo.delete(posts.get(getAdapterPosition()).getId());
                        itemClickListener.refresh();
                        Toast.makeText(view.getContext(), "Đã xóa khỏi yêu thích", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

    public interface OnItemClickListener {
        void refresh();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}


