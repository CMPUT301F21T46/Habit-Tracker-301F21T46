package com.example.habit_tracker_301f21t46;

import java.util.ArrayList;

public class Habit {
    private String title;
    private String reason;
    private String startDate;
    private boolean completed;
    //TODO Implement plan
    //TODO Implement visual progress indicator


    public Habit(String title, String reason, String startDate){
        this.title = title;
        this.reason = reason;
        this.startDate = startDate;
        this.completed = false; //TODO this needs to be reset at the beginning of everyday in the habit plan
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
}
