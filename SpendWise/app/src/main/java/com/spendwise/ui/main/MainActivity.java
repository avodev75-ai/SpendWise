package com.spendwise.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.spendwise.R;
import com.spendwise.ui.add.AddTransactionActivity;
import com.spendwise.ui.history.HistoryFragment;

public class MainActivity extends AppCompatActivity {

    TransactionViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        FloatingActionButton fab = findViewById(R.id.fab_add);

        // Load default fragment
        loadFragment(new DashboardFragment());

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_dashboard) {
                loadFragment(new DashboardFragment());
                return true;
            } else if (id == R.id.nav_history) {
                loadFragment(new HistoryFragment());
                return true;
            }
            return false;
        });

        fab.setOnClickListener(v -> {
            startActivity(new Intent(this, AddTransactionActivity.class));
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
