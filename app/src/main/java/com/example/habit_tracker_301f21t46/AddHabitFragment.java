package com.example.habit_tracker_301f21t46;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class AddHabitFragment extends DialogFragment {
    private EditText title;
    private EditText reason;
    private EditText date;

    private TextView displayDateView;
    private String strDate;

    private DatePickerDialog.OnDateSetListener dateSetListener;


    private OnFragmentInteractionListener listener;

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

        /*
        displayDateView.setOnClickListener(new View.OnClickListener() {
            //Create date picker
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
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
                strDate = year + "-" + month + "-" + day;
            }
        };

         */

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

                    }
                }).create();


    }

}
