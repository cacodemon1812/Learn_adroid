package jmaster.io.section1.adapter;

import android.app.Activity;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jmaster.io.section1.R;
import jmaster.io.section1.model.Category;

public class MyListAdapter extends BaseAdapter {

    private final Activity context;
    private List<Category> categoryList;

    public MyListAdapter(Activity context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categoryList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.list_view_item, container, false);
        }

        ((TextView) convertView.findViewById(R.id.textViewTitle))
                .setText(categoryList.get(position).getName());

        ((TextView) convertView.findViewById(R.id.textViewDescription))
                .setText(categoryList.get(position).getDescription());

        return convertView;
    }

}
