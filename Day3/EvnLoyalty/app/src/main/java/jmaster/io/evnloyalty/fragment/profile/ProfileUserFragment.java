package jmaster.io.evnloyalty.fragment.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.activity.MainActivity;
import jmaster.io.evnloyalty.model.User;
import jmaster.io.evnloyalty.service.SharePrefService;

public class ProfileUserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_profile_user, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
        initGoBack();
        initEditProfile();
    }

    private void initData( ){
        SharePrefService sharePrefService = new SharePrefService(getActivity());
        User user = new Gson().fromJson(sharePrefService.getString(SharePrefService.KEY_USER), User.class);

        ((TextView) getView().findViewById(R.id.txtName)).setText(user.getName());
        ((TextView) getView().findViewById(R.id.txtDate)).setText(user.getRoles().toString());
        ((TextView) getView().findViewById(R.id.txtPhone)).setText(user.getPhone());
        ((TextView) getView().findViewById(R.id.txtAddress)).setText(user.getAddress());
        ((TextView) getView().findViewById(R.id.txtPoint)).setText(String.valueOf(user.getPoint()));
    }

    public void initGoBack() {
        ((ImageView) getView().findViewById(R.id.imageBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    public void initEditProfile() {
        ((ImageView) getView().findViewById(R.id.editImage)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setFragment(EditProfileFragment.class,null);
            }
        });
    }
}