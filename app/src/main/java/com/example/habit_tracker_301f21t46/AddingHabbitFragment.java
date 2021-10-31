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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    private Button confirm, cancel;
    private String pickerDate;

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

        DatePicker();

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
