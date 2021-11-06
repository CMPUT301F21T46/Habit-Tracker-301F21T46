package com.example.habit_tracker_301f21t46;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Fragment for indicating days of week when the habit takes place.
 * Linked to fragment_plan layout
 */
public class PlanFragment extends Fragment {

    private Button mon_btn,tue_btn,wed_btn,thu_btn,fri_btn,sat_btn,sun_btn;

    private ArrayList<Habit> mon_list,tue_list,wed_list,thu_list,fri_list,sat_list,sun_list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan,container,false);

        ArrayList<Habit> habitlist = HabitData.getInstance().getHabitList();

        mon_list= new ArrayList<>();
        tue_list= new ArrayList<>();
        wed_list= new ArrayList<>();
        thu_list= new ArrayList<>();
        fri_list= new ArrayList<>();
        sat_list= new ArrayList<>();
        sun_list= new ArrayList<>();

        mon_btn=view.findViewById(R.id.Mon_btn);
        tue_btn=view.findViewById(R.id.Tue_btn);
        wed_btn=view.findViewById(R.id.Wed_btn);
        thu_btn=view.findViewById(R.id.Thu_btn);
        fri_btn=view.findViewById(R.id.Fri_btn);
        sat_btn=view.findViewById(R.id.Sat_btn);
        sun_btn=view.findViewById(R.id.Sun_btn);

        for(Habit h:habitlist){
            if(h.getDays_of_week()!=null && h.getDays_of_week().contains("monday")&&!mon_list.contains(h)){
                mon_list.add(h);
                Log.d(TAG, "mon");
            }
            if(h.getDays_of_week()!=null && h.getDays_of_week().contains("tuesday")&&!tue_list.contains(h)){
                tue_list.add(h);
                Log.d(TAG, "tue");
            }
            if(h.getDays_of_week()!=null && h.getDays_of_week().contains("wednesday")&&!wed_list.contains(h)){
                wed_list.add(h);
                Log.d(TAG, "wed");
            }
            if(h.getDays_of_week()!=null && h.getDays_of_week().contains("thursday")&&!thu_list.contains(h)){
                thu_list.add(h);
                Log.d(TAG, "thur");
            }
            if(h.getDays_of_week()!=null && h.getDays_of_week().contains("friday")&&!fri_list.contains(h)){
                fri_list.add(h);
                Log.d(TAG, "fri");
            }
            if(h.getDays_of_week()!=null && h.getDays_of_week().contains("saturday")&&!sat_list.contains(h)) {
                sat_list.add(h);
                Log.d(TAG, "sat");
            }
            if(h.getDays_of_week()!=null && h.getDays_of_week().contains("sunday")&&!sun_list.contains(h)){
                sun_list.add(h);
                Log.d(TAG, "sun");
            }
        }

        ListView listView = (ListView) view.findViewById(R.id.habits_list);
        SingleHabitListAdapter mon_adapter = new SingleHabitListAdapter(view.getContext(), R.layout.habit_view_layout,mon_list);
        SingleHabitListAdapter tue_adapter = new SingleHabitListAdapter(view.getContext(), R.layout.habit_view_layout,tue_list);
        SingleHabitListAdapter wed_adapter = new SingleHabitListAdapter(view.getContext(), R.layout.habit_view_layout,wed_list);
        SingleHabitListAdapter thu_adapter = new SingleHabitListAdapter(view.getContext(), R.layout.habit_view_layout,thu_list);
        SingleHabitListAdapter fri_adapter = new SingleHabitListAdapter(view.getContext(), R.layout.habit_view_layout,fri_list);
        SingleHabitListAdapter sat_adapter = new SingleHabitListAdapter(view.getContext(), R.layout.habit_view_layout,sat_list);
        SingleHabitListAdapter sun_adapter = new SingleHabitListAdapter(view.getContext(), R.layout.habit_view_layout,sun_list);

        mon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(mon_adapter);
            }
        });
        tue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(tue_adapter);
            }
        });
        wed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(wed_adapter);
            }
        });
        thu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(thu_adapter);
            }
        });
        fri_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(fri_adapter);
            }
        });
        sat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(sat_adapter);
            }
        });
        sun_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(sun_adapter);
            }
        });

        return view;
    }
}
