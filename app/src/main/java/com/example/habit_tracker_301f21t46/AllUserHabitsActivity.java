package com.example.habit_tracker_301f21t46;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllUserHabitsActivity extends AppCompatActivity implements AddHabitFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_habits);
        HabitData habitData = HabitData.getInstance();

        ListView listView = (ListView) findViewById(R.id.habits_list);
        listView.setAdapter(HabitData.getInstance().getSingleHabitListAdapter());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Todo: click is only registering on the name
                HabitData.getInstance().setSelectedHabitIndex(i);
                startHabitDetailsActivity();
            }
        });

        // adding habits
        final FloatingActionButton addCityButton = findViewById(R.id.add_habit_button);
        addCityButton.setOnClickListener((v) -> {
            new AddHabitFragment().show(getSupportFragmentManager(), "ADD_HABIT");
        });
    }

    private void startHabitDetailsActivity() {
        Intent intent = new Intent(this, HabitDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onOkPressed(Habit newHabit) {
        // tf is this for
    }

}