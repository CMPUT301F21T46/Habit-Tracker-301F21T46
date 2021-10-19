package com.example.habit_tracker_301f21t46;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.habit_tracker_301f21t46.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    Button allHabits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting up toolbar and navigation drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*
        Could open a fragment right away or load data into homepage directly
        if (savedInstanceState == null)
         */

        //allHabits = findViewById(R.id.button2);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(HabitData.getInstance(this,R.layout.activity_main).getHabitListAdapter());

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                startHabitDetailsActivity();
                return false;
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_list:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ListFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_following:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FollowersFragment()).commit();
                break;
            case R.id.nav_followers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FollowersFragment()).commit();
                break;
                //Add more cases to send user to other activities
        }
        drawer.closeDrawer(GravityCompat.START); //Close the drawer when an item is selected
        return true;
    }

    @Override
    public void onBackPressed() {
        //We don't wont to leave the activity
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void startHabitDetailsActivity() {
        Intent intent = new Intent(this, HabitDetailsActivity.class);
        startActivity(intent);
    }

    private void startAllHabits() {
        Intent intent = new Intent(this, AllUserHabitsActivity.class);
        startActivity(intent);
    }

}