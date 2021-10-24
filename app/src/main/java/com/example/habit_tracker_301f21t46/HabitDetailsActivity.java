package com.example.habit_tracker_301f21t46;

import androidx.annotation.NonNull;
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

import java.util.Calendar;

public class HabitDetailsActivity extends AppCompatActivity{
    private Button confirmChangesButton, deleteMedButton;
    private String title, reason, date;
    private TextView displayDateView;
    private EditText editTitleView, editReasonView;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    private HabitData habitData = HabitData.getInstance();
    private Habit selectedHabit = habitData.getHabitList().get(habitData.getSelectedHabitIndex());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_details);

        //Find views
        confirmChangesButton = findViewById(R.id.confirmChangesButton);
        deleteMedButton = findViewById(R.id.deleteMedButton);
        displayDateView = (TextView) findViewById(R.id.edit_date_view);
        editTitleView = findViewById(R.id.edit_name_view);
        editReasonView = findViewById(R.id.edit_reason_view);
        //Set med detail
        displayDateView.setText(selectedHabit.getStartDate());
        editTitleView.setText(selectedHabit.getTitle());
        editReasonView.setText(selectedHabit.getReason());
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
        //Set user input on chosen Medicine object
        selectedHabit.setTitle(title);
        selectedHabit.setReason(reason);
        selectedHabit.setStartDate(date);
        habitData.getSingleHabitListAdapter().notifyDataSetChanged();
    }

    private void deleteMed() {
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