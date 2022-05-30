package jmaster.io.evnloyalty.fragment.home;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
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
        return inflater.inflate(R.layout.fragment_home, container, false);
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

                testNotification();
            }
            return false;
        });
    }

    private void testNotification() {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line Much longer text that cannot fit one lineMuch longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        createNotificationChannel();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "CHANNEL_ID";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}