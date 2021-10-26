package com.example.habit_tracker_301f21t46;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Calendar;

public class HabitDetailsActivity extends AppCompatActivity{
    // FireBase
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    String userID;
    // UI
    private Button confirmChangesButton, deleteMedButton;
    private String title, reason, date;
    private TextView displayDateView;
    private EditText editTitleView, editReasonView;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    // Get Data Instances
    private HabitData habitData = HabitData.getInstance();
    private Habit selectedHabit = habitData.getHabitList().get(habitData.getSelectedHabitIndex());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_details);

        // Getting FireBase Instances
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid(); // retrieve ID of current logged in user

        //Find views
        confirmChangesButton = findViewById(R.id.confirmChangesButton);
        deleteMedButton = findViewById(R.id.deleteMedButton);
        displayDateView = (TextView) findViewById(R.id.edit_date_view);
        editTitleView = findViewById(R.id.edit_name_view);
        editReasonView = findViewById(R.id.edit_reason_view);

        //Testing fetching Data from FireBase
        DocumentReference documentReference = mStore.collection("habits").document(
                "2efb9b77-e09c-45d7-a403-10d97b0cc452");//Todo: make it global selectedHabit.getHabitID());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                editTitleView.setText(value.getString("habitTitle"));
                editReasonView.setText(value.getString("habitReason"));
                displayDateView.setText(value.getString("startDate"));
            }
        });

        //Date Picker Stuff
        date = selectedHabit.getStartDate();
        displayDateView.setOnClickListener(new View.OnClickListener() {
            //Create date picker
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        HabitDetailsActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            //Set date from user input on date picker
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date = year + "-" + month + "-" + day;
            }
        };

        //Todo: new details need to be store in FireStore
        confirmChangesButton.setOnClickListener(new View.OnClickListener() {
            //Get the user input, check validity, set changes and end activity
            @Override
            public void onClick(View view) {
                getInput();
                //if(checkInput()) {
                //Todo: Implement input checks if needed
                    setChanges();
                    Toast.makeText(getApplicationContext(),
                            "Changes confirmed",
                            Toast.LENGTH_LONG).show();
                    endActivity();
                //}
            }
        });

        //Todo: habit needs to deleted off FireBase
        deleteMedButton.setOnClickListener(new View.OnClickListener() {
            //Delete med and end activity
            @Override
            public void onClick(View view) {
                deleteMed();
                endActivity();
            }
        });
    }

    private void getInput(){
        //Get user input from views
        title = editTitleView.getText().toString();
        reason = editReasonView.getText().toString();
    }

    private void setChanges(){
        //Todo: Removed this once FireStore is global?
        //Set user input on chosen Medicine object
        selectedHabit.setTitle(title);
        selectedHabit.setReason(reason);
        selectedHabit.setStartDate(date);
        habitData.getSingleHabitListAdapter().notifyDataSetChanged();
    }

    private void deleteMed() {
        //Todo: Removed this once FireStore is global?
        //Delete Chosen Medicine object
        habitData.getHabitList().remove(selectedHabit);
        habitData.getSingleHabitListAdapter().notifyDataSetChanged();
    }

    private void endActivity() {
        //Goes back to MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}