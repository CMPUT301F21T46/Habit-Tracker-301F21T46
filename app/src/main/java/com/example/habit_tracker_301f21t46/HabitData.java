package com.example.habit_tracker_301f21t46;

import android.content.Context;

import java.util.ArrayList;

public class HabitData {
    //Holds all the habits for a specific user (each user will have their own habit data)
    //Defines a custom arrayAdapter to be used to display habits
    //Todo: rename to global data

    private static HabitData instance;
    private ArrayList<Habit> habitList;
    private HabitListAdapter habitListAdapter;

    private ArrayList<Habit> singleHabitList;
    private SingleHabitListAdapter singleHabitListAdapter;

    private int habitIndex;


    private HabitData(Context activity, int layout){
        habitList = new ArrayList<Habit>();
        habitListAdapter = new HabitListAdapter(activity, R.layout.custom_habit_view_layout, habitList);

        singleHabitList = new ArrayList<Habit>();
        singleHabitListAdapter = new SingleHabitListAdapter(activity, R.layout.habit_view_layout, habitList);

        //Adding test data
        Habit habit1 = new Habit("Walk Dog", "He's fat", "2000-11-11");
        Habit habit2 = new Habit("Got to gym", "My gf left me", "2001-12-12");
        Habit habit3 = new Habit("Vibe", "My gf left me", "2001-12-12");
        Habit habit4 = new Habit("yeet", "My gf left me", "2001-12-12");
        habitList.add(habit1);
        habitList.add(habit2);
        habitList.add(habit3);
        habitList.add(habit4);
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
    public ArrayList<Habit> getSingleHabitList() {
        return singleHabitList;
    }
    public void setSingleHabitList(ArrayList<Habit> singleHabitList) {
        this.singleHabitList = singleHabitList;
    }
    public SingleHabitListAdapter getSingleHabitListAdapter() {
        return singleHabitListAdapter;
    }
    public void setSingleHabitListAdapter(SingleHabitListAdapter singleHabitListAdapter) {
        this.singleHabitListAdapter = singleHabitListAdapter;
    }

    public int getHabitIndex() {
        return habitIndex;
    }

    public void setHabitIndex(int habitIndex) {
        this.habitIndex = habitIndex;
    }
}

