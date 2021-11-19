package com.example.habit_tracker_301f21t46;

public class HabitEventData {
    private String comment;
    private String location;
    private String date;
    private String photo;

    public HabitEventData(CharSequence date) {
        this.comment = "";
        this.location = "";
        this.photo = "";
        this.date = (String) date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
