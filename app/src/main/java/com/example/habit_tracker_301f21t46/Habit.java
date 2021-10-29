package com.example.habit_tracker_301f21t46;

import java.util.List;
import java.util.UUID;

public class Habit {
    private String title;
    private String reason;
    private String startDate;
    //private boolean completed;
    private String habitID;
    //public static final int SUNDAY = 1, MONDAY = 2, TUESDAY = 3, WEDNESDAY = 4, THURSDAY = 5, FRIDAY = 6, SATURDAY = 7;
    private List<Integer> Day_of_week;

    //TODO Implement plan
    //TODO Implement visual progress indicator
    public boolean is_Sun(){return Day_of_week.contains(1);}
    public boolean is_Mon(){return Day_of_week.contains(2);}
    public boolean is_Tue(){return Day_of_week.contains(3);}
    public boolean is_Wed(){return Day_of_week.contains(4);}
    public boolean is_Thur(){return Day_of_week.contains(5);}
    public boolean is_Fri(){return Day_of_week.contains(6);}
    public boolean is_Sat(){return Day_of_week.contains(7);}


    public Habit(String title, String reason, String startDate,List<Integer> days){
        this.title = title;
        this.reason = reason;
        this.startDate = startDate;
        Day_of_week = days;
        habitID = UUID.randomUUID().toString();
        //this.completed = false; //TODO this needs to be reset at the beginning of everyday in the habit plan
    }

    //----- Getters and Setters -----
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<Integer> getDay_of_week() {
        return Day_of_week;
    }

    public void setDay_of_week(List<Integer> daysOfWeek) {
        this.Day_of_week = daysOfWeek;
    }

    public String getHabitID() {
        return habitID;
    }

    public void setHabitID(String habitID) {
        this.habitID = habitID;
    }
}
