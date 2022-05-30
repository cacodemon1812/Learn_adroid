package jmaster.io.section1.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import jmaster.io.section1.R;

public class CategoryCreateFragment extends Fragment {

    public CategoryCreateFragment() {
        super(R.layout.fragment_create_category);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(R.string.tao_moi_danh_muc);
    }

}
