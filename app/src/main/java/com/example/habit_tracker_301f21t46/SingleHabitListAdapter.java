package com.example.habit_tracker_301f21t46;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 *HabitList adapter for unexpanded habit (on homepage)
 */
public class SingleHabitListAdapter extends ArrayAdapter<Habit> {
    //FireBase
    //UI
    private final Context habitListAdaptercontex;
    private final int habitListAdapterResource;
    //Constructor
    public SingleHabitListAdapter(Context context, int resource, ArrayList<Habit> objects) {
        super(context, resource, objects);
        habitListAdaptercontex = context;
        habitListAdapterResource = resource;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the Habit details
        String title = getItem(position).getTitle();
        //Create Layout Inflater
        LayoutInflater inflater = LayoutInflater.from(habitListAdaptercontex);
        convertView = inflater.inflate(habitListAdapterResource, parent, false);
        //Get views and set Data to display
        TextView tvTitle = (TextView) convertView.findViewById(R.id.single_habit_view);
        tvTitle.setText(title);
        return convertView;
    }
}
