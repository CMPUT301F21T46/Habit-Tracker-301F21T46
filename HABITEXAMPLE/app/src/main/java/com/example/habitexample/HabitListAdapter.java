package com.example.habitexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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
        Button comment = (Button) convertView.findViewById(R.id.commentOnHabitButton);
        Button photo = (Button) convertView.findViewById(R.id.addPhotoHabitButton);
        Button location = (Button) convertView.findViewById(R.id.addLocationHabitButton);
        comment.setVisibility(View.GONE);
        photo.setVisibility(View.GONE);
        location.setVisibility(View.GONE);

        EditText writtenComment = (EditText) convertView.findViewById(R.id.commentOnHabitEditText);
        Button confirmCommentButton = (Button) convertView.findViewById(R.id.confirm_button);
        writtenComment.setVisibility(View.GONE);
        confirmCommentButton.setVisibility(View.GONE);
        TextView comment_habit = (TextView) convertView.findViewById(R.id.habit_comment);
        comment_habit.setVisibility(View.GONE);

        Button confirmPhotoButton = (Button) convertView.findViewById(R.id.confirm_photo_button);
        confirmPhotoButton.setVisibility(View.GONE);

        tvTitle.setText(title);
        CheckBox habitDone = (CheckBox) convertView.findViewById(R.id.habitCompleted);

        View finalConvertView = convertView;
        habitDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isClicked) {
                if (isClicked == true) {
                    //habitDone.setChecked(true);
                    comment.setVisibility(View.VISIBLE);
                    photo.setVisibility(View.VISIBLE);
                    location.setVisibility(View.VISIBLE);

                } else {
                    comment.setVisibility(View.GONE);
                    photo.setVisibility(View.GONE);
                    location.setVisibility(View.GONE);
                    writtenComment.setVisibility(View.GONE);
                    confirmCommentButton.setVisibility(View.GONE);
                    // Will need to delete any info if have added a comment/photo/location
                }

                comment.setOnClickListener(new View.OnClickListener() { // if user wants to add a comment

                    // need confirm and cancel button
                    // also need to make sure comment has max limit
                    @Override
                    public void onClick(View view) {
                        writtenComment.setVisibility(View.VISIBLE);
                        confirmCommentButton.setVisibility(View.VISIBLE);
                    }
                });
                confirmCommentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String habitComment = writtenComment.getText().toString();

                        Habit obj = new Habit(title, reason, startDate, habitComment);

                        comment_habit.setText(habitComment);
                        HabitData habitData = HabitData.getInstance();
                        habitData.getHabitList().set(position, obj);
                        //habitData.getHabitListAdapter().notifyDataSetChanged(); not sure if we need this

                        writtenComment.setVisibility(View.GONE);
                        writtenComment.getText().clear();
                        confirmCommentButton.setVisibility(View.GONE);
                        comment_habit.setVisibility(View.VISIBLE);

                    }
                });

                photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirmPhotoButton.setVisibility(View.VISIBLE);
                    }
                });
                confirmPhotoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // adds photo to array
                    }
                });


        }
    });
        return convertView;
}
}