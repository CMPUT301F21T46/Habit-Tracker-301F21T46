package com.example.habit_tracker_301f21t46;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Model class that holds data for a habit.
 * atrributes -
 * title: title of habit
 * reason: reason of doing habit
 * startDate: date when habit was started
 * completed: boolean indicating whether habit is completed or not.
 * habitID: unique ID of a habit
 * habitevent: HabitEvent object to be initialized on habit creation
 * days_of_week: ArrayList<String> containing days of week habit takes place.
 */
public class Habit {
    private String title;
    private String reason;
    private String startDate;
    private boolean completed;
    private String habitID;
    private HabitEvent habitEvent;
    private ArrayList<String> days_of_week;

    public Habit(String title, String reason, String startDate, String habitID, ArrayList<String> days_of_week){
        this.title = title;
        this.reason = reason;
        this.startDate = startDate;
        this.habitID = habitID;
        this.completed = false; //TODO this needs to be reset at the beginning of everyday in the habit plan
        this.habitEvent = new HabitEvent();
        this.days_of_week = days_of_week;
    }

    //----- Getters and Setters -----

    /**
     * getter to get habit title
     * @return String title containing habit title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter to set habit title
     * @param title (String) containing habit title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getter to get habit reason
     * @return String reason containing habit reason
     */
    public String getReason() {
        return reason;
    }
    /**
     * setter to set habit reason
     * @param reason (String) containing habit reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * getter to get habit start date
     * @return String startDate containing habit start date
     */
    public String getStartDate() {
        return startDate;
    }
    /**
     * setter to set habit start date
     * @param startDate (String) containing habit start date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * getter to get habit ID
     * @return String habitID containing habit ID
     */
    public String getHabitID() { return habitID; }

    /**
     * setter to set habit ID
     * @param habitID (String) containing habit ID
     */
    public void setHabitID(String habitID) { this.habitID = habitID; }

    /**
     * getter to get habit event
     * @return HabitEvent habitevent containing initialized habit event object
     */
    public HabitEvent getHabitEvent() { return habitEvent; }

    /**
     * getter to get days of week
     * @return ArrayList<String> days_of_week containg days of week for habit
     */
    public ArrayList<String> getDays_of_week() { return days_of_week; }

    /**
     * setter to set days of week
     * @param days_of_week (ArrayList<String>) days_of_week containg days of week for habit
     */
    public void setDays_of_week(ArrayList<String> days_of_week) { this.days_of_week = days_of_week; }
}
