package com.example.habit_tracker_301f21t46;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class HabitListAdapter extends ArrayAdapter<Habit> {
    //Defines a custom ListAdapter to display the habits
    // Todo: This should only be concerned with displaying data move habit event stuff somewhere else

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

        //Create Layout Inflater
        LayoutInflater inflater = LayoutInflater.from(habitListAdaptercontex);
        convertView = inflater.inflate(habitListAdapterResource, parent, false);

        //Get views and set Data to display
        TextView tvTitle = (TextView) convertView.findViewById(R.id.textView1);
        Button comment = (Button) convertView.findViewById(R.id.commentOnHabitButton);
        Button photo = (Button) convertView.findViewById(R.id.addPhotoHabitButton);
        Button location = (Button) convertView.findViewById(R.id.addLocationHabitButton);
        comment.setVisibility(View.GONE);
        photo.setVisibility(View.GONE);
        location.setVisibility(View.GONE);

        EditText writtenComment = (EditText) convertView.findViewById(R.id.commentOnHabitEditText);
        Button confirmButton = (Button) convertView.findViewById(R.id.confirm_button);
        writtenComment.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);

        tvTitle.setText(title);
        CheckBox habitDone = (CheckBox) convertView.findViewById(R.id.habitCompleted);

        habitDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isClicked) {
                if (isClicked == true) {
                    //habitDone.setChecked(true);
                    comment.setVisibility(View.VISIBLE);
                    photo.setVisibility(View.VISIBLE);
                    location.setVisibility(View.VISIBLE);
                    comment.setOnClickListener(new View.OnClickListener() {

                        // need confirm and cancel button
                        // also need to make sure comment has max limit
                        @Override
                        public void onClick(View view) {
                            writtenComment.setVisibility(View.VISIBLE);
                            confirmButton.setVisibility(View.VISIBLE);

                            confirmButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String habitComment = writtenComment.getText().toString();
                                    writtenComment.setVisibility(View.GONE);
                                    confirmButton.setVisibility(View.GONE);
                                }
                            });
                        }
                    });
                } else {
                    comment.setVisibility(View.GONE);
                    photo.setVisibility(View.GONE);
                    location.setVisibility(View.GONE);
                    writtenComment.setVisibility(View.GONE);
                    confirmButton.setVisibility(View.GONE);
                    // Will need to delete any info if have added a comment/photo/location
                }
            }
        });
        return convertView;
    }
}