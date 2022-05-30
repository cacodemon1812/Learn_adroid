package jmaster.io.evnloyalty.fragment.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.activity.MainActivity;
import jmaster.io.evnloyalty.fragment.blog.BlogFragment;
import jmaster.io.evnloyalty.fragment.notification.NotificationFragment;
import jmaster.io.evnloyalty.fragment.profile.ProfileFragment;
import jmaster.io.evnloyalty.model.User;
import jmaster.io.evnloyalty.service.SharePrefService;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupBottomNeu();
        initData();
    }

    public void initData() {
        TextView txtName = (TextView) getView().findViewById(R.id.txtName);
        TextView txtDate = (TextView) getView().findViewById(R.id.txtDate);

        SharePrefService sharePrefService = new SharePrefService(getActivity());
        User user = new Gson().fromJson(sharePrefService.getString(SharePrefService.KEY_USER), User.class);
        txtName.setText("Hi, " + user.getName());
        txtDate.setText(user.getPhone());

    }

    public void setupBottomNeu() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) getView().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setItemIconTintList(null);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menuHome) {
                item.setChecked(true);
            }
            if (item.getItemId() == R.id.menuBlog) {
                ((MainActivity) getActivity()).setFragment(BlogFragment.class, null);
                item.setChecked(true);
            }
            if (item.getItemId() == R.id.menuProfile) {
                ((MainActivity) getActivity()).setFragment(ProfileFragment.class, null);
                item.setChecked(true);
            }
            if (item.getItemId() == R.id.menuNotification) {
                ((MainActivity) getActivity()).setFragment(NotificationFragment.class, null);
                item.setChecked(true);
            }
            return false;
        });
    }

}