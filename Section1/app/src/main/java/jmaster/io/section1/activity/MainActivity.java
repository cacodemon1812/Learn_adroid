package jmaster.io.section1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import jmaster.io.section1.R;
import jmaster.io.section1.fragment.CategoryCreateFragment;
import jmaster.io.section1.fragment.CategoryListFragment;
import jmaster.io.section1.fragment.WelcomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                boolean canback = getSupportFragmentManager().getBackStackEntryCount() > 0;
                getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuHome) {
                    Bundle extras = getIntent().getExtras();

                    // Extract data using passed keys
                    String username = extras.getString("username");

                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container_view, WelcomeFragment.class, bundle)
                            .commit();
                    return true;
                } else if (item.getItemId() == R.id.menuDanhMuc) {
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container_view, CategoryListFragment.class, null)
                            .commit();

                    return true;
                } else if (item.getItemId() == R.id.menuThongBao) {
                    return true;
                } else if (item.getItemId() == R.id.menuHoSo) {
                    return true;
                }

                return false;
            }
        });

        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.menuHome); // change to whichever id should be default
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //Title bar back press triggers onBackPressed()
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}