package jmaster.io.evnloyalty.fragment.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.activity.LoginActivity;
import jmaster.io.evnloyalty.activity.MainActivity;
import jmaster.io.evnloyalty.fragment.blog.BlogFavFragment;
import jmaster.io.evnloyalty.service.SharePrefService;

public class ProfileMenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile_menu, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        share();
        logout();
        fav();
        about();
    }

    public void share() {
        getView().findViewById(R.id.txtShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "EVN Loyalty");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Tải app tại https://www.evn.com.vn");
                startActivity(sendIntent);
            }
        });
    }

    public void logout() {
        getView().findViewById(R.id.txtLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePrefService sharePrefService = new SharePrefService(getActivity());
                sharePrefService.remove(SharePrefService.KEY_AUTHEN);

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void fav() {
        getView().findViewById(R.id.txtFav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(BlogFavFragment.class, null);
            }
        });
    }

    public void about() {
        getView().findViewById(R.id.txtAbout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.evn.com.vn/"));
                startActivity(intent);
            }
        });
    }
}