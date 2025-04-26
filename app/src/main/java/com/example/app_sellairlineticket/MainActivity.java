package com.example.app_sellairlineticket;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.app_sellairlineticket.Fragments.AccountFragment;
import com.example.app_sellairlineticket.Fragments.HistoryFragment;
import com.example.app_sellairlineticket.Fragments.HomeFragment;
import com.example.app_sellairlineticket.Fragments.NotifycationFragment;
import com.example.app_sellairlineticket.Model.AirportSearch;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    public static final int REQUEST_CODE_SEARCH = 1001;
    public static final String RESULT_SELECTED_AIRPORT = "selected_airport";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        loadFragment(new HomeFragment());
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                int id = item.getItemId();
                if(id == R.id.navigation_home){
                    fragment = new HomeFragment();
                } else if (id == R.id.navigation_list) {
                    fragment = new HistoryFragment();
                } else if (id == R.id.navigation_notifications) {
                    fragment = new NotifycationFragment();
                }else {
                    fragment = new AccountFragment();
                }
                return loadFragment(fragment);
            }
        });
    }
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String name_airport = getIntent().getStringExtra("Name_Airport");
        String sourceEdt = getIntent().getStringExtra("source");
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (fragment instanceof HomeFragment) {
            ((HomeFragment) fragment).updateEditextSearchAirportFrom(name_airport,sourceEdt);
        }
    }
}