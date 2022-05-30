package jmaster.io.section1.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

import jmaster.io.section1.R;
import jmaster.io.section1.adapter.MyListAdapter;
import jmaster.io.section1.model.Category;

public class CategoryListFragment extends Fragment {
    public CategoryListFragment() {
        super(R.layout.fragment_category);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.danh_muc);

        List<Category> list = Arrays.asList(new Category(1,"Quan","Quan dep"), new Category(2,"Ao","Ao dep"));

        MyListAdapter adapter = new MyListAdapter(getActivity(),list );

        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);


        view.findViewById(R.id.floating_action_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, CategoryCreateFragment.class, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
