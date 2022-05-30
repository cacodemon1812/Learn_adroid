package jmaster.io.evnloyalty.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.fragment.blog.BlogDetailFragment;
import jmaster.io.evnloyalty.fragment.blog.BlogFragment;
import jmaster.io.evnloyalty.fragment.home.HomeFragment;
import jmaster.io.evnloyalty.fragment.profile.EditProfileFragment;
import jmaster.io.evnloyalty.fragment.profile.ProfileFragment;
import jmaster.io.evnloyalty.service.SharePrefService;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharePrefService sharePrefService = new SharePrefService(this);
        String isAuthenticated = sharePrefService.getString(SharePrefService.KEY_AUTHEN);
        if (isAuthenticated.isEmpty()) {
            navigateLoginActivity();
            return;
        }

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, HomeFragment.class, null)
                .commit();
    }

    private void navigateLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.goBack();
        }
    }

    public void setFragment(Class<? extends Fragment> fragmentClass, Bundle bundle) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, fragmentClass, bundle)
                .addToBackStack(null)
                .commit();
    }
}