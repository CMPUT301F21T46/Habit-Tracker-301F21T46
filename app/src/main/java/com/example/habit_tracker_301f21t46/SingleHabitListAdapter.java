package com.example.habit_tracker_301f21t46;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SingleHabitListAdapter extends ArrayAdapter<Habit> {
    //Defines a custom ListAdapter to display the habits

    private final Context habitListAdaptercontex;
    private final int habitListAdapterResource;

    public SingleHabitListAdapter(Context context, int resource, ArrayList<Habit> objects) {
        super(context, resource, objects);
        habitListAdaptercontex = context;
        habitListAdapterResource = resource;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the Habit details
        String title = getItem(position).getTitle();

        //Create Layout Inflater
        LayoutInflater inflater = LayoutInflater.from(habitListAdaptercontex);
        convertView = inflater.inflate(habitListAdapterResource, parent, false);

        //Get views and set Data to display
        TextView tvTitle = (TextView) convertView.findViewById(R.id.single_habit_view);

        tvTitle.setText(title);

        return convertView;
    }
}
