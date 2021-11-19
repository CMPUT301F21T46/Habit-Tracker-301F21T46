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

/**
 * Holds all the habits for a specific user (each user will have their own habit data)
 * attributes -
 * habitList: ArrayList<Habit> that stores all habits for a user
 * HabitListAdapter and SingleHabitListAdapter: adapter for display habits for a user
 * selectedHabitIndex: array index for selected habit for a user
 */
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
                    ArrayList<String> days_of_week = (ArrayList<String>) doc.getData().get("days_of_week");
                    habitList.add(new Habit(habitTitle, habitReason, habitStartDate, habitID, days_of_week));
                }
                singleHabitListAdapter.notifyDataSetChanged();
                habitListAdapter.notifyDataSetChanged();
            }
        });

    }

    /**
     * exposes HabitData as a singleton so data can be accessed from other classes
     * @param activity: get current activity as Context objects
     * @param layout
     * @return instance (HabitData object) as a singleton
     */
    public static HabitData getInstance(Context activity, int layout){
        if (instance == null){
            instance = new HabitData(activity,layout);
        }
        return instance;
    }

    // ----- Getters and Setters -----

    /**
     * getter for HabitData instance (used if instance has habitdata) (class shadowing)
     * @return HabitData instance
     */
    public static HabitData getInstance() {
        return instance;
    }

    /**
     * setter for HabitData instance
     * @param instance HabitData object
     */
    public static void setInstance(HabitData instance) {
        HabitData.instance = instance;
    }

    /**
     * getter for HabitList
     * @return habitList (ArrayList<Habit>) containing habits for user
     */
    public ArrayList<Habit> getHabitList() {
        return habitList;
    }

    /**
     * setter for HabitList
     * @param habitList (ArrayList<Habit>) containing habits for user
     */
    public void setHabitList(ArrayList<Habit> habitList) {
        this.habitList = habitList;
    }
    /**
     * getter for HabitListAdapter
     * @return habitListAdapter (HabitListAdapter) containing an adapter for HabitList
     */
    public HabitListAdapter getHabitListAdapter() {
        return habitListAdapter;
    }
    /**
     * setter for HabitListAdapter
     * @param habitListAdapter (HabitListAdapter) containing an adapter for HabitList
     */
    public void setHabitListAdapter(HabitListAdapter habitListAdapter) {
        this.habitListAdapter = habitListAdapter;
    }
    /**
     * getter for singleHabitListAdapter
     * @return singleHabitListAdapter (SingleHabitListAdapter) containing an adapter for HabitList
     */
    public SingleHabitListAdapter getSingleHabitListAdapter() {
        return singleHabitListAdapter;
    }
    /**
     * setter for singleHabitListAdapter
     * @param singleHabitListAdapter (SingleHabitListAdapter) containing an adapter for HabitList
     */
    public void setSingleHabitListAdapter(SingleHabitListAdapter singleHabitListAdapter) {
        this.singleHabitListAdapter = singleHabitListAdapter;
    }
    /**
     * getter for selectedHabitIndex
     * @return selectedHabitIndex (int) containing an index for selected habit
     */
    public int getSelectedHabitIndex() {
        return selectedHabitIndex;
    }
    /**
     * getter for selectedHabitIndex
     * @param selectedHabitIndex (int) containing an index for selected habit
     */
    public void setSelectedHabitIndex(int selectedHabitIndex) {
        this.selectedHabitIndex = selectedHabitIndex;
    }
}

