package com.example.habit_tracker_301f21t46;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HabitListAdapter extends ArrayAdapter<Habit> {
    //Defines a custom ListAdapter to display the habits

    private Context habitListAdaptercontex;
    private int habitListAdapterResource;

    public HabitListAdapter(Context context, int resource, ArrayList<Habit> objects) {
        super(context, resource, objects);
        habitListAdaptercontex = context;
        habitListAdapterResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the Habit details
        String title = getItem(position).getTitle();
        String reason = getItem(position).getReason();
        String startDate = getItem(position).getStartDate();

        //Create Layout Inflater
        LayoutInflater inflater = LayoutInflater.from(habitListAdaptercontex);
        convertView = inflater.inflate(habitListAdapterResource, parent, false);

        //Get views and set Data to display
        TextView tvTitle = (TextView) convertView.findViewById(R.id.textView1);

        tvTitle.setText(title);

        return convertView;
    }
}
