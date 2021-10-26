package com.example.habit_tracker_301f21t46;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitData initInstance = HabitData.getInstance(this,R.layout.activity_main);

        //Setting up toolbar and navigation drawer
        // Todo: figure out how to make the drawer global (appear on all other activities)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Start HomePage fragment //Todo fix rotation reset bug
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomePageFragment()).commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // when an item on the drawer menu is clicked fragment/activity will start
        switch (item.getItemId()) {
            case R.id.nav_list:
                // starts AllUserHabitsActivity
                Intent intent = new Intent(this, AllUserHabitsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_following:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FollowersFragment()).commit();
                break;
            case R.id.nav_homePage:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomePageFragment()).commit();
                break;
            case R.id.nav_Logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
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
}