package com.example.habit_tracker_301f21t46;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

@RunWith(JUnit4.class)
public class HabitTest {

    Habit getHabitobject(String title, String reason, String startDate, String habitID, ArrayList<String> days_of_week){
        Habit habit = new Habit(title, reason, startDate, habitID, days_of_week);
        return habit;
    }

    @Test
    public void getTitle() {
        ArrayList<String> days_of_week = new ArrayList<String>();
        String title = "Do Yoga!";
        Habit habit = getHabitobject(title, "NA", "NA", "NA", days_of_week);
        String title1 = habit.getTitle();
        assertEquals(title, title1);
    }

    @Test
    public void setTitle() {
        ArrayList<String> days_of_week = new ArrayList<String>();
        Habit habit = getHabitobject("NA", "NA", "NA", "NA", days_of_week);
        String title = "Do Yoga!";
        habit.setTitle(title);
        Habit habit1 = getHabitobject(title, "NA", "NA", "NA", days_of_week);
        assertTrue(habit1.equals(habit));
    }

    @Test
    public void getReason() {
        ArrayList<String> days_of_week = new ArrayList<String>();
        String reason = "To stay fit!";
        Habit habit = getHabitobject("NA", reason, "NA", "NA", days_of_week);
        String reason1 = habit.getReason();
        assertEquals(reason, reason1);
    }

    @Test
    public void setReason() {
        ArrayList<String> days_of_week = new ArrayList<String>();
        Habit habit = getHabitobject("NA", "NA", "NA", "NA", days_of_week);
        String reason = "To stay fit!";
        habit.setReason(reason);
        Habit habit1 = getHabitobject("NA", reason, "NA", "NA", days_of_week);
        assertTrue(habit1.equals(habit));
    }

    @Test
    public void getStartDate() {
        ArrayList<String> days_of_week = new ArrayList<String>();
        String date = "04/22/2000";
        Habit habit = getHabitobject("NA", "NA", date, "NA", days_of_week);
        String date1 = habit.getStartDate();
        assertEquals(date, date1);
    }

    @Test
    public void setStartDate() {
        ArrayList<String> days_of_week = new ArrayList<String>();
        Habit habit = getHabitobject("NA", "NA", "NA", "NA", days_of_week);
        String date = "04/22/2000";
        habit.setStartDate(date);
        Habit habit1 = getHabitobject("NA", "NA", date, "NA", days_of_week);
        assertTrue(habit1.equals(habit));
    }

    @Test
    public void getHabitID() {
        ArrayList<String> days_of_week = new ArrayList<String>();
        String ID = "ABC9876";
        Habit habit = getHabitobject("NA", "NA", "NA", ID, days_of_week);
        String ID1 = habit.getHabitID();
        assertEquals(ID, ID1);
    }

    @Test
    public void setHabitID() {
        ArrayList<String> days_of_week = new ArrayList<String>();
        Habit habit = getHabitobject("NA", "NA", "NA", "NA", days_of_week);
        String ID = "ABC9876";
        habit.setHabitID(ID);
        Habit habit1 = getHabitobject("NA", "NA", "NA", ID, days_of_week);
        assertTrue(habit1.equals(habit));
    }

    @Test
    public void getHabitEvent() {
        ArrayList<String> days_of_week = new ArrayList<String>();
        Habit habit = getHabitobject("NA", "NA", "NA", "NA", days_of_week);
        HabitEvent habitevent = habit.getHabitEvent();
        assertNotNull("habit event exists", habitevent);
    }

    @Test
    public void getDays_of_week() {
        ArrayList<String> days_of_week = new ArrayList<String>();
        days_of_week.add("mon");
        Habit habit = getHabitobject("NA", "NA", "NA", "NA", days_of_week);
        ArrayList<String> days_of_week1 = habit.getDays_of_week();
        assertEquals(days_of_week, days_of_week1);
    }

    @Test
    public void setDays_of_week() {
        ArrayList<String> days_of_week = new ArrayList<String>();
        Habit habit = getHabitobject("NA", "NA", "NA", "NA", days_of_week);
        days_of_week.add("mon");
        habit.setDays_of_week(days_of_week);
        Habit habit1 = getHabitobject("NA", "NA", "NA", "NA", days_of_week);
        assertTrue(habit1.equals(habit));
    }
}