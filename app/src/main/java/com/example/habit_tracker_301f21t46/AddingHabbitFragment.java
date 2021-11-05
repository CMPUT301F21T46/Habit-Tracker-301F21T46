package com.example.habit_tracker_301f21t46;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Fragments that handles adding habits (through a dialog box).
 * Linked to fragment_adding_habit.
 * Takes in details like title, reason, start date from UI.
 * The date is selected via a datepicker
 */
public class AddingHabbitFragment extends Fragment {
    // FireBase
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    String userEmail;
    // UI
    private EditText title;
    private EditText reason;
    private TextView displayDateView;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private Button mon_btn,tue_btn,wed_btn,thu_btn,fri_btn,sat_btn,sun_btn;
    private Button confirm, cancel;
    private Switch publicSwitch;
    private String pickerDate;
    private boolean publicHabit = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adding_habit,container,false);

        title = view.findViewById(R.id.habbit_title_view);
        reason = view.findViewById(R.id.habbit_reason_view);
        //Todo implement DatePicker
        displayDateView = (TextView) view.findViewById(R.id.date_picker_view);
        confirm = view.findViewById(R.id.confirm_new_habbit_button);
        cancel = view.findViewById(R.id.button);
        mon_btn = view.findViewById(R.id.Mon_btn);
        tue_btn = view.findViewById(R.id.Tue_btn);
        wed_btn = view.findViewById(R.id.Wed_btn);
        thu_btn = view.findViewById(R.id.Thu_btn);
        fri_btn = view.findViewById(R.id.Fri_btn);
        sat_btn = view.findViewById(R.id.Sat_btn);
        sun_btn = view.findViewById(R.id.Sun_btn);
        publicSwitch = view.findViewById(R.id.public_switch_view);

        final ArrayList<String> days_of_week=new ArrayList<String>();

        DatePicker();

        mon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!days_of_week.contains("monday")) {
                    days_of_week.add("monday");
                    mon_btn.setBackgroundColor(Color.rgb(75, 0, 130));
                }
            }
        });
        tue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!days_of_week.contains("tuesday")) {
                    days_of_week.add("tuesday");
                    tue_btn.setBackgroundColor(Color.rgb(75, 0, 130));
                }
            }
        });
        wed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!days_of_week.contains("wednesday")) {
                    days_of_week.add("wednesday");
                    wed_btn.setBackgroundColor(Color.rgb(75, 0, 130));
                }
            }
        });
        thu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!days_of_week.contains("thursday")) {
                    days_of_week.add("thursday");
                    thu_btn.setBackgroundColor(Color.rgb(75, 0, 130));
                }
            }
        });
        fri_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!days_of_week.contains("friday")) {
                    days_of_week.add("friday");
                    fri_btn.setBackgroundColor(Color.rgb(75, 0, 130));
                }
            }
        });
        sat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!days_of_week.contains("saturday")) {
                    days_of_week.add("saturday");
                    sat_btn.setBackgroundColor(Color.rgb(75, 0, 130));
                }
            }
        });
        sun_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!days_of_week.contains("sunday")) {
                    days_of_week.add("sunday");
                    sun_btn.setBackgroundColor(Color.rgb(75, 0, 130));
                }
            }
        });

        publicSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicHabit = !publicHabit;
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTitle = title.getText().toString();
                String newReason = reason.getText().toString();
                String newDate = pickerDate;
                String uniqueID = UUID.randomUUID().toString();

                //Add Habit to FireBase
                mAuth = FirebaseAuth.getInstance();
                mStore = FirebaseFirestore.getInstance();
                userEmail = mAuth.getCurrentUser().getEmail();
                DocumentReference documentReference = mStore.collection(userEmail).document(uniqueID);
                Map<String, Object> habit = new HashMap<>();
                habit.put("habitTitle", newTitle);
                habit.put("habitReason", newReason);
                habit.put("startDate", newDate);
                habit.put("habitID", uniqueID);
                habit.put("days_of_week", days_of_week);
                habit.put("publicHabit", publicHabit);
                documentReference.set(habit);

                AllHabitsFragment nextFrag = new AllHabitsFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setText("");
                reason.setText("");
                displayDateView.setText("");

                AllHabitsFragment nextFrag = new AllHabitsFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    // Todo: this can be a class of its own
    private void DatePicker() {
        displayDateView.setOnClickListener(new View.OnClickListener() {
            //Create date picker
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
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
                pickerDate = year + "-" + month + "-" + day;
                displayDateView.setText(pickerDate);
            }
        };
    }
}
