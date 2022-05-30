package jmaster.io.evnloyalty.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import jmaster.io.evnloyalty.R;
import jmaster.io.evnloyalty.fragment.blog.BlogDetailFragment;
import jmaster.io.evnloyalty.fragment.blog.BlogFragment;
import jmaster.io.evnloyalty.fragment.home.HomeFragment;
import jmaster.io.evnloyalty.fragment.profile.EditProfileFragment;
import jmaster.io.evnloyalty.fragment.profile.ProfileFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, HomeFragment.class, null)
                .commit();
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