package com.example.habitexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    boolean isClicked;
    private DrawerLayout drawer;
    Button allHabits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setContentView(R.layout.activity_main);

        /*
        Could open a fragment right away or load data into homepage directly
        if (savedInstanceState == null)
         */

        //allHabits = findViewById(R.id.button2);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(HabitData.getInstance(this,R.layout.activity_main).getHabitListAdapter());


        // when user checks off a habit
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                isClicked = true;
            }
        });
    }

}
