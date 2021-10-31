package com.example.habit_tracker_301f21t46;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HabitData {
    //Holds all the habits for a specific user (each user will have their own habit data)
    //Defines a custom arrayAdapter to be used to display habits
    //Todo: rename to global data
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;

    private static HabitData instance;
    private ArrayList<Habit> habitList;
    private HabitListAdapter habitListAdapter;
    private SingleHabitListAdapter singleHabitListAdapter;
    private int selectedHabitIndex;

    private HabitData(Context activity, int layout){
        //UI
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        //Adapters
        habitList = new ArrayList<Habit>();
        habitListAdapter = new HabitListAdapter(activity, R.layout.custom_habit_view_layout, habitList);
        singleHabitListAdapter = new SingleHabitListAdapter(activity, R.layout.habit_view_layout, habitList);

        final CollectionReference collectionReference = mStore.collection(mAuth.getCurrentUser().getEmail());

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                // Clear the old list
                habitList.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots)
                {
                    // Todo: Why are they getting added in weird positions?
                    // Todo: Adding a habit that already exist will update its details (add check)
                    String habitTitle = (String) doc.getData().get("habitTitle");
                    String habitReason = (String) doc.getData().get("habitReason");
                    String habitStartDate = (String) doc.getData().get("startDate");
                    String habitID = (String) doc.getData().get("habitID");
                    habitList.add(new Habit(habitTitle, habitReason, habitStartDate, habitID));
                }
                singleHabitListAdapter.notifyDataSetChanged();
                habitListAdapter.notifyDataSetChanged();
            }
        });

    }

    public static HabitData getInstance(Context activity, int layout){
        //Will expose HabitData as a singleton so data can be accessed from other classes
        if (instance == null){
            instance = new HabitData(activity,layout);
        }
        return instance;
    }

    // ----- Getters and Setters -----
    public static HabitData getInstance() {
        return instance;
    }
    public static void setInstance(HabitData instance) {
        HabitData.instance = instance;
    }
    public ArrayList<Habit> getHabitList() {
        return habitList;
    }
    public void setHabitList(ArrayList<Habit> habitList) {
        this.habitList = habitList;
    }
    public HabitListAdapter getHabitListAdapter() {
        return habitListAdapter;
    }
    public void setHabitListAdapter(HabitListAdapter habitListAdapter) {
        this.habitListAdapter = habitListAdapter;
    }
    public SingleHabitListAdapter getSingleHabitListAdapter() {
        return singleHabitListAdapter;
    }
    public void setSingleHabitListAdapter(SingleHabitListAdapter singleHabitListAdapter) {
        this.singleHabitListAdapter = singleHabitListAdapter;
    }

    public int getSelectedHabitIndex() {
        return selectedHabitIndex;
    }

    public void setSelectedHabitIndex(int selectedHabitIndex) {
        this.selectedHabitIndex = selectedHabitIndex;
    }
}

