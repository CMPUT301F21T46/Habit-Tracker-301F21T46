package com.example.habit_tracker_301f21t46;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllUserHabitsActivity extends AppCompatActivity implements AddHabitFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_habits);

        ListView listView = (ListView) findViewById(R.id.city_list);
        listView.setAdapter(HabitData.getInstance(this,R.layout.activity_main).getSingleHabitListAdapter());

        final FloatingActionButton addCityButton = findViewById(R.id.add_habit_button);
        addCityButton.setOnClickListener((v) -> {
            new AddHabitFragment().show(getSupportFragmentManager(), "ADD_HABIT");
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Sets index of chosen med and starts ModifyMedActivity
                HabitData.getInstance().setHabitIndex(i);
                startHabitDetailsActivity();
            }
        });
    }

    @Override
    public void onOkPressed(Habit newHabit) {

    }

    private void startHabitDetailsActivity() {
        Intent intent = new Intent(this, HabitDetailsActivity.class);
        startActivity(intent);
    }
}