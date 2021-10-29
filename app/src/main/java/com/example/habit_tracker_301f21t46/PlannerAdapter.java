package com.example.habit_tracker_301f21t46;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlannerAdapter extends ArrayAdapter<Habit> {

    private Context Adaptercontex;
    private int AdapterResource;

    public PlannerAdapter(Context context, int resource, ArrayList<Habit> objects) {
        super(context, resource, objects);
        Adaptercontex = context;
        AdapterResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the Habit details
        String title = getItem(position).getTitle();
        //TODO : add more info to items in planner
        String reason = getItem(position).getReason();
        String startDate = getItem(position).getStartDate();
        List<Integer> day_of_week= getItem(position).getDay_of_week();

        //Create Layout Inflater
        LayoutInflater inflater = LayoutInflater.from(Adaptercontex);
        convertView = inflater.inflate(AdapterResource, parent, false);

        //Get views and set Data to display
        TextView tvTitle = (TextView) convertView.findViewById(R.id.title_text);
        //TextView tvdofw = (TextView) convertView.findViewById(R.id.dofw_text);

        tvTitle.setText(title);


        return convertView;
    }
}
