package com.example.habit_tracker_301f21t46;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HabitData {
    //Holds all the habits for a specific user (each user will have their own habit data)
    //Defines a custom arrayAdapter to be used to display habits
    //Todo: rename to global data

    private static HabitData instance;
    private ArrayList<Habit> habitList;
    private HabitListAdapter habitListAdapter;

    //get the list of habits which contains each day of a week
    private final ArrayList<Habit> habitList_sun;
    private final ArrayList<Habit> habitList_mon;
    private final ArrayList<Habit> habitList_tue;
    private final ArrayList<Habit> habitList_wed;
    private final ArrayList<Habit> habitList_thur;
    private final ArrayList<Habit> habitList_fri;
    private final ArrayList<Habit> habitList_sat;

    private static HabitData instance_mon;
    private static HabitData instance_tue;
    private static HabitData instance_wed;
    private static HabitData instance_thur;
    private static HabitData instance_fri;
    private static HabitData instance_sat;
    private static HabitData instance_sun;

    private final PlannerAdapter mon_pa;
    private final PlannerAdapter tue_pa;
    private final PlannerAdapter wed_pa;
    private final PlannerAdapter thur_pa;
    private final PlannerAdapter fri_pa;
    private final PlannerAdapter sat_pa;
    private final PlannerAdapter sun_pa;

    private ArrayList<Habit> singleHabitList;
    private SingleHabitListAdapter singleHabitListAdapter;

    private int selectedHabitIndex;

    private HabitData(Context activity, int layout){
        habitList = new ArrayList<Habit>();
        habitListAdapter = new HabitListAdapter(activity, R.layout.custom_habit_view_layout, habitList);

        habitList_mon = new ArrayList<Habit>();
        habitList_tue = new ArrayList<Habit>();
        habitList_wed = new ArrayList<Habit>();
        habitList_thur = new ArrayList<Habit>();
        habitList_fri = new ArrayList<Habit>();
        habitList_sat = new ArrayList<Habit>();
        habitList_sun = new ArrayList<Habit>();

        singleHabitList = new ArrayList<Habit>();
        singleHabitListAdapter = new SingleHabitListAdapter(activity, R.layout.habit_view_layout, habitList);

        //
        List<Integer> everyday= Arrays.asList(1,2,3,4,5,6,7);
        List<Integer> MWF= Arrays.asList(2,4,6);

        //Adding test data
        Habit habit1 = new Habit("Walk Dog", "He's fat", "2000-11-11",everyday);
        Habit habit2 = new Habit("Got to gym", "My gf left me", "2001-12-12",MWF);
        Habit habit3 = new Habit("Vibe", "My gf left me", "2001-12-12",everyday);
        Habit habit4 = new Habit("yeet", "My gf left me", "2001-12-12",MWF);

        habitList.add(habit1);
        habitList.add(habit2);
        habitList.add(habit3);
        habitList.add(habit4);

        for(Habit h:habitList){
            if(h.is_Mon()){ habitList_mon.add(h);}
            if(h.is_Tue()){ habitList_tue.add(h);}
            if(h.is_Wed()){ habitList_wed.add(h);}
            if(h.is_Thur()){ habitList_thur.add(h);}
            if(h.is_Fri()){ habitList_fri.add(h);}
            if(h.is_Sat()){ habitList_sat.add(h);}
            if(h.is_Sun()){ habitList_sun.add(h);}
        }

        mon_pa = new PlannerAdapter(activity, R.layout.planner_item, habitList_mon);
        tue_pa = new PlannerAdapter(activity, R.layout.planner_item, habitList_tue);
        wed_pa= new PlannerAdapter(activity, R.layout.planner_item, habitList_wed);
        thur_pa = new PlannerAdapter(activity, R.layout.planner_item, habitList_thur);
        fri_pa = new PlannerAdapter(activity, R.layout.planner_item, habitList_fri);
        sat_pa = new PlannerAdapter(activity, R.layout.planner_item, habitList_sat);
        sun_pa = new PlannerAdapter(activity, R.layout.planner_item, habitList_sun);
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

    public int getSelectedHabitIndex() {
        return selectedHabitIndex;
    }

    public void setSelectedHabitIndex(int selectedHabitIndex) {
        this.selectedHabitIndex = selectedHabitIndex;
    }

    public PlannerAdapter getMon_pa() {
        return mon_pa;
    }

    public PlannerAdapter getTue_pa() {
        return tue_pa;
    }

    public PlannerAdapter getWed_pa() {
        return wed_pa;
    }

    public PlannerAdapter getThur_pa() {
        return thur_pa;
    }

    public PlannerAdapter getFri_pa() {
        return fri_pa;
    }

    public PlannerAdapter getSat_pa() {
        return sat_pa;
    }

    public PlannerAdapter getSun_pa() {
        return sun_pa;
    }

    public static HabitData getInstance_mon(Context activity, int layout){
        //Will expose HabitData as a singleton so data can be accessed from other classes
        if (instance_mon == null){
            instance_mon = new HabitData(activity,layout);
        }
        return instance_mon;
    }
    public static HabitData getInstance_tue(Context activity, int layout){
        //Will expose HabitData as a singleton so data can be accessed from other classes
        if (instance_tue == null){
            instance_tue = new HabitData(activity,layout);
        }
        return instance_tue;
    }
    public static HabitData getInstance_wed(Context activity, int layout){
        //Will expose HabitData as a singleton so data can be accessed from other classes
        if (instance_wed == null){
            instance_wed = new HabitData(activity,layout);
        }
        return instance_wed;
    }
    public static HabitData getInstance_thur(Context activity, int layout){
        //Will expose HabitData as a singleton so data can be accessed from other classes
        if (instance_thur == null){
            instance_thur = new HabitData(activity,layout);
        }
        return instance_thur;
    }
    public static HabitData getInstance_fri(Context activity, int layout){
        //Will expose HabitData as a singleton so data can be accessed from other classes
        if (instance_fri == null){
            instance_fri = new HabitData(activity,layout);
        }
        return instance_fri;
    }
    public static HabitData getInstance_sat(Context activity, int layout){
        //Will expose HabitData as a singleton so data can be accessed from other classes
        if (instance_sat == null){
            instance_sat = new HabitData(activity,layout);
        }
        return instance_sat;
    }
    public static HabitData getInstance_sun(Context activity, int layout){
        //Will expose HabitData as a singleton so data can be accessed from other classes
        if (instance_sun == null){
            instance_sun = new HabitData(activity,layout);
        }
        return instance_sun;
    }

}

