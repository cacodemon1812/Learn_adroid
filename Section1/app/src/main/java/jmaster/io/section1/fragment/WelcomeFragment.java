package jmaster.io.section1.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import jmaster.io.section1.R;

public class WelcomeFragment extends Fragment {
    public WelcomeFragment() {
        super(R.layout.fragment_welcome);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.trang_chu);
        String username = requireArguments().getString("username");
        ((TextView) view.findViewById(R.id.textViewUsername)).setText(username);
    }
}
