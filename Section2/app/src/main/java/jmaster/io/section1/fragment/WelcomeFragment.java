package jmaster.io.section1.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import jmaster.io.section1.R;
import jmaster.io.section1.activity.LoginActivity;
import jmaster.io.section1.activity.MainActivity;

public class WelcomeFragment extends Fragment {
    public WelcomeFragment() {
        super(R.layout.fragment_welcome);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.trang_chu);
        //doc tu SharedPreferences ra
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("MySession", Context.MODE_PRIVATE);
        String username  = sharedpreferences.getString("username","");

        ((TextView) view.findViewById(R.id.textViewUsername)).setText(username);

        ((Button) view.findViewById(R.id.buttonLogout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferences.edit().clear().commit();
                navigateLoginActivity();
            }
        });
    }

    private void navigateLoginActivity () {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
