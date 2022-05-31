package jmaster.io.evnloyalty.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.activity.MainActivity;
import jmaster.io.evnloyalty.fragment.blog.BlogDetailFragment;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

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
                    ///luu du lieu bai viet uu thich xuong database
                }
            });

            txtName.setOnClickListener(new BlogItemStyle2Adapter.ViewHolder.BlogDetailListener());
            blogImage.setOnClickListener(new BlogItemStyle2Adapter.ViewHolder.BlogDetailListener());
        }

        private class BlogDetailListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                if (view.getContext() instanceof MainActivity) {
                    ((MainActivity) view.getContext()).setFragment(BlogDetailFragment.class, null);
                }
            }
        }
    }
}
