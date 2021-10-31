package com.example.habit_tracker_301f21t46;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HabitDetailsFragment extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit_details,container,false);

        // Getting FireBase Instances
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid(); // retrieve ID of current logged in user

        //Find views
        confirmChangesButton = view.findViewById(R.id.confirmChangesButton);
        deleteMedButton = view.findViewById(R.id.deleteMedButton);
        displayDateView = (TextView) view.findViewById(R.id.edit_date_view);
        editTitleView = view.findViewById(R.id.edit_name_view);
        editReasonView = view.findViewById(R.id.edit_reason_view);

        DatePicker();

        //Testing fetching Data from FireBase
        DocumentReference documentReference = mStore.collection(mAuth.getCurrentUser().getEmail()).document(
                habitData.getHabitList().get(habitData.getSelectedHabitIndex()).getHabitID());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot doc, @Nullable FirebaseFirestoreException error) {
                // Todo: this can be improved
                editTitleView.setText(doc.getString("habitTitle"));
                editReasonView.setText(doc.getString("habitReason"));
                displayDateView.setText(doc.getString("startDate"));
            }
        });

        confirmChangesButton.setOnClickListener(new View.OnClickListener() {
            //Get the user input, check validity, set changes and end activity
            @Override
            public void onClick(View view) {
                Map<String, Object> habit = new HashMap<>();
                habit.put("habitID", habitData.getHabitList().get(habitData.getSelectedHabitIndex()).getHabitID());
                habit.put("habitTitle", editTitleView.getText().toString());
                habit.put("habitReason", editReasonView.getText().toString());
                habit.put("startDate", date);
                documentReference.set(habit);

                AllHabitsFragment nextFrag = new AllHabitsFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        deleteMedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Todo: Deleting is really buggy
                documentReference.delete();

                AllHabitsFragment nextFrag = new AllHabitsFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private void DatePicker() {
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
                date = year + "-" + month + "-" + day;
            }
        };
    }
}
