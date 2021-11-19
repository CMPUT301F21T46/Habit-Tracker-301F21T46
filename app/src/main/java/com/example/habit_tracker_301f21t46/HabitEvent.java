package com.example.habit_tracker_301f21t46;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains a Habit Event for a habit for an user
 * attribute:
 * comment : (String) comment for a Habit Event
 * location : (String) location for a Habit Event
 */
public class HabitEvent{

    private String comment;
    private String location;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    private String habitID;
    public HabitEventData habitEventData;

    public HabitEvent(String habitID) {
        this.habitID = habitID;
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
    }

    public void Initialize(View habitView) {
        CheckBox habitDone = (CheckBox) habitView.findViewById(R.id.habitCompleted);
        TextView title = (TextView) habitView.findViewById(R.id.textView1);

        habitDone.setOnCheckedChangeListener((CompoundButton compoundButton, boolean isClicked) -> {
            onChecked(habitView, isClicked);
        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  onTitleClick(view);
                //title.setText("it works");
            }
        });

    }

    private void updateComment(String comment){
        Date d = new Date();
        String s = (String) DateFormat.format("yyyy/MM/dd", d.getTime());
        String date = s.replaceAll("/","-");
        final DocumentReference documentReference = mStore.collection(mAuth.getCurrentUser().getEmail()).document(habitID).collection("HabitEvent").document(date);
        documentReference.update("comment", comment);
    }

    private void updateLocation(String location){
        Date d = new Date();
        String s = (String) DateFormat.format("yyyy/MM/dd", d.getTime());
        String date = s.replaceAll("/","-");
        final DocumentReference documentReference = mStore.collection(mAuth.getCurrentUser().getEmail()).document(habitID).collection("HabitEvent").document(date);
        documentReference.update("location", location);
    }

    private void onChecked(View habitView, boolean isClicked) {
        Button addPhoto = (Button) habitView.findViewById(R.id.addPhotoHabitButton);
        Button addLocation = (Button) habitView.findViewById(R.id.addLocationHabitButton);
        Button addComment = (Button) habitView.findViewById(R.id.commentOnHabitButton);
        if (isClicked) {
            final DocumentReference documentReference = mStore.collection(mAuth.getCurrentUser().getEmail()).document(habitID);
            addComment.setVisibility(View.VISIBLE);
            addPhoto.setVisibility(View.VISIBLE);
            addLocation.setVisibility(View.VISIBLE);
            Date d = new Date();
            CharSequence s = DateFormat.format("yyyy/MM/dd", d.getTime());
            habitEventData = new HabitEventData(s);
            String date = habitEventData.getDate();
            date = date.replaceAll("/","-");
            Map<String, Object> habitevent = new HashMap<>();
            habitevent.put("date", date);
            habitevent.put("comment", "");
            habitevent.put("photo", "");
            habitevent.put("location","");
            documentReference.collection("HabitEvent").document(date).set(habitevent);
            addComment.setOnClickListener((View view) -> {
                addComment(habitView, habitEventData);
            });
            addLocation.setOnClickListener((View view) -> {
                addLocation(habitView, habitEventData);
            });
            //TextView habitEventComment = (TextView) habitView.findViewById(R.id.habitEventComment);
            //String comment = (String) habitEventComment.getText();
            //habitEventData.setComment(comment);
            //documentReference.collection("HabitEvent").document(date).set(habitEventData);
        } else {
            // Will need to delete any info if have added a comment/photo/location
            comment = "";
            location = "";

            cancelComment(habitView);
            cancelLocation(habitView);
            addComment.setVisibility(View.GONE);
            addPhoto.setVisibility(View.GONE);
            addLocation.setVisibility(View.GONE);
        }
    }

    private HabitEventData addComment(View habitView, HabitEventData habitEventData) {

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
            confirmComment(habitView, habitEventData);
        });

        cancelButton.setOnClickListener((View view)  -> {
            cancelComment(habitView);
        });

        return habitEventData;
    }

    private void cancelComment(View habitView) {

        Button addComment = (Button) habitView.findViewById(R.id.commentOnHabitButton);
        TextView habitEventComment = (TextView) habitView.findViewById(R.id.habitEventComment);
        EditText writtenComment = (EditText) habitView.findViewById(R.id.commentOnHabitEditText);
        Button confirmButton = (Button) habitView.findViewById(R.id.confirmCommentButton);
        Button cancelButton = (Button) habitView.findViewById(R.id.cancelCommentButton);
        LinearLayout confirmButtons = (LinearLayout) habitView.findViewById(R.id.confirmCommentButtons);

        if (comment == null){
            habitEventComment.setVisibility(View.GONE);
        }
        else{ //if commentEmpty == false
            habitEventComment.setVisibility(View.VISIBLE);
            addComment.setText("EDIT COMMENT");
        }

        addComment.setVisibility(View.VISIBLE);
        writtenComment.setVisibility(View.GONE);
        confirmButtons.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
    }

    private void confirmComment(View habitView, HabitEventData habitEventData) {

        Button addComment = (Button) habitView.findViewById(R.id.commentOnHabitButton);
        TextView habitEventComment = (TextView) habitView.findViewById(R.id.habitEventComment);
        EditText writtenComment = (EditText) habitView.findViewById(R.id.commentOnHabitEditText);

        comment = writtenComment.getText().toString();
        updateComment(comment);
        habitEventData.setComment(comment);
        habitEventComment.setText(comment);
        cancelComment(habitView);
    }

    private void addLocation(View habitView, HabitEventData habitEventData) {
        Button addLocation = (Button) habitView.findViewById(R.id.addLocationHabitButton);
        TextView habitEvenLocation = (TextView) habitView.findViewById(R.id.habitEventLocation);

        EditText writtenLocation = (EditText) habitView.findViewById(R.id.locationOnHabitEditText);
        Button confirmButton = (Button) habitView.findViewById(R.id.confirmLocationButton);
        Button cancelButton = (Button) habitView.findViewById(R.id.cancelLocationButton);
        LinearLayout confirmButtons = (LinearLayout) habitView.findViewById(R.id.confirmLocationButtons);

        writtenLocation.setText(location);
        addLocation.setVisibility(View.GONE);
        habitEvenLocation.setVisibility(View.GONE);
        writtenLocation.setVisibility(View.VISIBLE);
        confirmButtons.setVisibility(View.VISIBLE);
        confirmButton.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.VISIBLE);

        confirmButton.setOnClickListener((View view) -> {
            confirmLocation(habitView, habitEventData);
        });

        cancelButton.setOnClickListener((View view)  -> {
            cancelLocation(habitView);
        });
    }

    private void cancelLocation(View habitView) {

        Button addLocation = (Button) habitView.findViewById(R.id.addLocationHabitButton);
        TextView habitEventLocation = (TextView) habitView.findViewById(R.id.habitEventLocation);
        EditText writtenLocation = (EditText) habitView.findViewById(R.id.locationOnHabitEditText);
        Button confirmButton = (Button) habitView.findViewById(R.id.confirmLocationButton);
        Button cancelButton = (Button) habitView.findViewById(R.id.cancelLocationButton);
        LinearLayout confirmButtons = (LinearLayout) habitView.findViewById(R.id.confirmLocationButtons);

        System.out.println(location);
        if (location == null || location == ""){
            habitEventLocation.setVisibility(View.GONE);
            addLocation.setText("ADD LOCATION");
        } else{
            habitEventLocation.setVisibility(View.VISIBLE);
            addLocation.setText("EDIT LOCATION");
        }

        addLocation.setVisibility(View.VISIBLE);
        writtenLocation.setVisibility(View.GONE);
        confirmButtons.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
    }

    private void confirmLocation(View habitView, HabitEventData habitEventData) {
        Button addLocation = (Button) habitView.findViewById(R.id.addLocationHabitButton);
        TextView habitEventLocation = (TextView) habitView.findViewById(R.id.habitEventLocation);
        EditText writtenLocation = (EditText) habitView.findViewById(R.id.locationOnHabitEditText);

        location = writtenLocation.getText().toString();
        updateLocation(location);
        habitEventData.setLocation(location);
        habitEventLocation.setText(location);
        cancelLocation(habitView);
    }

}