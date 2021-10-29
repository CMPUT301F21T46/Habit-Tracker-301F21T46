package com.example.habit_tracker_301f21t46;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Planner extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        ListView listView1 = (ListView) findViewById(R.id.Mon_list);
        listView1.setAdapter(HabitData.getInstance_mon(this,R.layout.activity_planner).getMon_pa());
        ListView listView2 = (ListView) findViewById(R.id.Tue_list);
        listView2.setAdapter(HabitData.getInstance_tue(this,R.layout.activity_planner).getTue_pa());
        ListView listView3 = (ListView) findViewById(R.id.Wed_list);
        listView3.setAdapter(HabitData.getInstance_wed(this,R.layout.activity_planner).getWed_pa());
        ListView listView4 = (ListView) findViewById(R.id.Thur_list);
        listView4.setAdapter(HabitData.getInstance_thur(this,R.layout.activity_planner).getThur_pa());
        ListView listView5 = (ListView) findViewById(R.id.Fri_list);
        listView5.setAdapter(HabitData.getInstance_fri(this,R.layout.activity_planner).getFri_pa());
        ListView listView6 = (ListView) findViewById(R.id.Sat_list);
        listView6.setAdapter(HabitData.getInstance_sat(this,R.layout.activity_planner).getSat_pa());
        ListView listView7 = (ListView) findViewById(R.id.Sun_list);
        listView7.setAdapter(HabitData.getInstance_sun(this,R.layout.activity_planner).getSun_pa());

    }
}