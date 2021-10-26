package com.example.habit_tracker_301f21t46;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddHabitFragment extends DialogFragment {
    // FireBase
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    String userID;
    // UI
    private EditText title;
    private EditText reason;
    private EditText date;
    private TextView displayDateView;
    private String strDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    private OnFragmentInteractionListener listener; // ?? tf is this for
    public interface OnFragmentInteractionListener{
        void onOkPressed(Habit newHabit);
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        } else{
            throw new RuntimeException(context.toString()
                + "must implement OnFragmentInteractionListener");
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_habit_fragment_layout,null);
        title = view.findViewById(R.id.title_editText);
        reason = view.findViewById(R.id.reason_editText);
        //Todo implement DatePicker
        date = view.findViewById(R.id.date_editText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        return builder
                .setView(view)
                .setTitle("New Habit")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newTitle = title.getText().toString();
                        String newReason = reason.getText().toString();
                        String newDate = date.getText().toString();
                        Habit newHabit = new Habit(newTitle, newReason, newDate);

                        HabitData habitData = HabitData.getInstance();
                        habitData.getHabitList().add(newHabit);
                        habitData.getSingleHabitListAdapter().notifyDataSetChanged();

                        //Add Habit to FireBase
                        mAuth = FirebaseAuth.getInstance();
                        mStore = FirebaseFirestore.getInstance();
                        userID = mAuth.getCurrentUser().getUid(); // retrieve ID of current logged in user
                        DocumentReference documentReference = mStore.collection("habits").document(newHabit.getHabitID());
                        Map<String, Object> habit = new HashMap<>();
                        habit.put("habitTitle", newHabit.getTitle());
                        habit.put("habitReason", newHabit.getReason());
                        habit.put("startDate", newHabit.getStartDate());
                        habit.put("owner", userID);
                        documentReference.set(habit).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "Habit has been added");
                            }
                        });
                    }
                }).create();
    }
}
