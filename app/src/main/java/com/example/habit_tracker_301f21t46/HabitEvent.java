package com.example.habit_tracker_301f21t46;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

public class HabitEvent {

    private String comment;
    private ImageView photograph;
    private String location;

    public HabitEvent() {
        this.comment = "";
        this.location = "";
    }

    public void Initialize(View habitView) {
        CheckBox habitDone = (CheckBox) habitView.findViewById(R.id.habitCompleted);
        Button photo = (Button) habitView.findViewById(R.id.addPhotoHabitButton);
        Button location = (Button) habitView.findViewById(R.id.addLocationHabitButton);
        Button comment = (Button) habitView.findViewById(R.id.commentOnHabitButton);
        EditText writtenComment = (EditText) habitView.findViewById(R.id.commentOnHabitEditText);
        Button confirmButton = (Button) habitView.findViewById(R.id.confirm_button);

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

    }
}
