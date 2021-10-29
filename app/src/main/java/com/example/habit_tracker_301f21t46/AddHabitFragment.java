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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private Button sun;
    private Button mon;
    private Button tue;
    private Button wed;
    private Button thu;
    private Button fri;
    private Button sat;

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

        sun = view.findViewById(R.id.button1);
        mon = view.findViewById(R.id.button2);
        tue = view.findViewById(R.id.button3);
        wed = view.findViewById(R.id.button4);
        thu = view.findViewById(R.id.button5);
        fri = view.findViewById(R.id.button6);
        sat = view.findViewById(R.id.button7);

        List<Integer> dofw = new ArrayList<Integer>();

        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dofw.contains(1)){
                    dofw.remove(Integer.valueOf(1));
                }
                else{
                    dofw.add(1);
                }
            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dofw.contains(2)){
                    dofw.remove(Integer.valueOf(2));
                }
                else{
                    dofw.add(2);
                }
            }
        });
        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dofw.contains(3)){
                    dofw.remove(Integer.valueOf(3));
                }
                else{
                    dofw.add(3);
                }
            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dofw.contains(4)){
                    dofw.remove(Integer.valueOf(4));
                }
                else{
                    dofw.add(4);
                }
            }
        });
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dofw.contains(5)){
                    dofw.remove(Integer.valueOf(5));
                }
                else{
                    dofw.add(5);
                }
            }
        });
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dofw.contains(6)){
                    dofw.remove(Integer.valueOf(6));
                }
                else{
                    dofw.add(6);
                }
            }
        });
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dofw.contains(7)){
                    dofw.remove(Integer.valueOf(7));
                }
                else{
                    dofw.add(7);
                }
            }
        });

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
                        //TODO: if(!dofw.isEmpty())
                        Habit newHabit = new Habit(newTitle, newReason, newDate, dofw);

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
                        habit.put("dayofweek",dofw);
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
