package com.example.habit_tracker_301f21t46;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        Habit currentHabit = getItem(position);
        String title = currentHabit.getTitle();

        //Create Layout Inflater
        LayoutInflater inflater = LayoutInflater.from(habitListAdaptercontex);
        convertView = inflater.inflate(habitListAdapterResource, parent, false);

        //Get views and set Data to display
        TextView tvTitle = (TextView) convertView.findViewById(R.id.textView1);
        TextView habitEventComment = (TextView) convertView.findViewById(R.id.habitEventComment);
        TextView habitEventLocation = (TextView) convertView.findViewById(R.id.habitEventLocation);
        Button comment = (Button) convertView.findViewById(R.id.commentOnHabitButton);
        Button photo = (Button) convertView.findViewById(R.id.addPhotoHabitButton);
        Button location = (Button) convertView.findViewById(R.id.addLocationHabitButton);

        EditText writtenComment = (EditText) convertView.findViewById(R.id.commentOnHabitEditText);
        EditText writtenLocation = (EditText) convertView.findViewById(R.id.locationOnHabitEditText);

        ImageView habitPhoto = (ImageView) convertView.findViewById(R.id.habitPhoto);

        LinearLayout confirmButtons = (LinearLayout) convertView.findViewById(R.id.confirmCommentButtons);
        Button cancelButton = (Button) convertView.findViewById(R.id.cancelCommentButton);
        Button confirmButton = (Button) convertView.findViewById(R.id.confirmCommentButton);
        LinearLayout confirmLocationButtons = (LinearLayout) convertView.findViewById(R.id.confirmLocationButtons);
        Button cancelLocationButton = (Button) convertView.findViewById(R.id.cancelLocationButton);
        Button confirmLocationButton = (Button) convertView.findViewById(R.id.confirmLocationButton);

        habitEventComment.setVisibility(View.GONE);
        habitEventLocation.setVisibility(View.GONE);
        confirmButtons.setVisibility(View.GONE);
        confirmLocationButtons.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);
        cancelLocationButton.setVisibility(View.GONE);
        confirmLocationButton.setVisibility(View.GONE);
        habitPhoto.setVisibility(View.GONE);
        comment.setVisibility(View.GONE);
        photo.setVisibility(View.GONE);
        location.setVisibility(View.GONE);
        writtenComment.setVisibility(View.GONE);
        writtenLocation.setVisibility(View.GONE);

        tvTitle.setText(title);

        // initialize the habit event stuff
        currentHabit.getHabitEvent().Initialize(convertView);

        return convertView;
    }
}