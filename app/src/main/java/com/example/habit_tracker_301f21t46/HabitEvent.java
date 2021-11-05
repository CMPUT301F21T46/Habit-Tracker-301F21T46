package com.example.habit_tracker_301f21t46;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Contains a Habit Event for a habit for an user
 * attribute:
 * comment : (String) comment for a Habit Event
 * location : (String) location for a Habit Event
 */
public class HabitEvent {

    private String comment;
    private String location;

    public HabitEvent() {

    }

    public void Initialize(View habitView) {
        CheckBox habitDone = (CheckBox) habitView.findViewById(R.id.habitCompleted);

        habitDone.setOnCheckedChangeListener((CompoundButton compoundButton, boolean isClicked) -> {
            onChecked(habitView, isClicked);
        });

    }

    private void onChecked(View habitView, boolean isClicked) {
        Button addPhoto = (Button) habitView.findViewById(R.id.addPhotoHabitButton);
        Button addLocation = (Button) habitView.findViewById(R.id.addLocationHabitButton);
        Button addComment = (Button) habitView.findViewById(R.id.commentOnHabitButton);
        if (isClicked) {
            addComment.setVisibility(View.VISIBLE);
            addPhoto.setVisibility(View.VISIBLE);
            addLocation.setVisibility(View.VISIBLE);

            addComment.setOnClickListener((View view) -> {
                addComment(habitView);
            });
        } else {
            // Will need to delete any info if have added a comment/photo/location
            comment = "";

            cancelComment(habitView);
            addComment.setVisibility(View.GONE);
            addPhoto.setVisibility(View.GONE);
            addLocation.setVisibility(View.GONE);
        }
    }

    private void addComment(View habitView) {
        Button addComment = (Button) habitView.findViewById(R.id.commentOnHabitButton);
        TextView habitEventComment = (TextView) habitView.findViewById(R.id.habitEventComment);

        EditText writtenComment = (EditText) habitView.findViewById(R.id.commentOnHabitEditText);
        Button confirmButton = (Button) habitView.findViewById(R.id.confirmCommentButton);
        Button cancelButton = (Button) habitView.findViewById(R.id.cancelCommentButton);
        LinearLayout confirmButtons = (LinearLayout) habitView.findViewById(R.id.confirmCommentButtons);

        writtenComment.setText(comment);
        addComment.setVisibility(View.GONE);
        habitEventComment.setVisibility(View.GONE);
        writtenComment.setVisibility(View.VISIBLE);
        confirmButtons.setVisibility(View.VISIBLE);
        confirmButton.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.VISIBLE);

        confirmButton.setOnClickListener((View view) -> {
            confirmComment(habitView);
        });

        cancelButton.setOnClickListener((View view)  -> {
            cancelComment(habitView);
        });
    }

    private void cancelComment(View habitView) {
        Button addComment = (Button) habitView.findViewById(R.id.commentOnHabitButton);
        TextView habitEventComment = (TextView) habitView.findViewById(R.id.habitEventComment);
        EditText writtenComment = (EditText) habitView.findViewById(R.id.commentOnHabitEditText);
        Button confirmButton = (Button) habitView.findViewById(R.id.confirmCommentButton);
        Button cancelButton = (Button) habitView.findViewById(R.id.cancelCommentButton);
        LinearLayout confirmButtons = (LinearLayout) habitView.findViewById(R.id.confirmCommentButtons);

        boolean commentEmpty = comment.equals("");
        habitEventComment.setVisibility(commentEmpty ? View.GONE : View.VISIBLE);
        addComment.setText(commentEmpty ? "ADD COMMENT" : "EDIT COMMENT");
        addComment.setVisibility(View.VISIBLE);
        writtenComment.setVisibility(View.GONE);
        confirmButtons.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
    }

    private void confirmComment(View habitView) {
        Button addComment = (Button) habitView.findViewById(R.id.commentOnHabitButton);
        TextView habitEventComment = (TextView) habitView.findViewById(R.id.habitEventComment);
        EditText writtenComment = (EditText) habitView.findViewById(R.id.commentOnHabitEditText);

        comment = writtenComment.getText().toString();

        habitEventComment.setText(comment);
        cancelComment(habitView);
    }
}
