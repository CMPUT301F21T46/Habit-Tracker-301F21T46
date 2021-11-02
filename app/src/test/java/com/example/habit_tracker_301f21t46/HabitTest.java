package com.example.habit_tracker_301f21t46;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HabitTest {

    Habit getHabitobject(String title, String reason, String startDate, String habitID){
        Habit habit = new Habit(title, reason, startDate, habitID);
        return habit;
    }

    @Test
    public void getTitle() {
        String title = "Do Yoga!";
        Habit habit = getHabitobject(title, "NA", "NA", "NA");
        String title1 = habit.getTitle();
        assertEquals(title, title1);
    }

    @Test
    public void setTitle() {
        Habit habit = getHabitobject("NA", "NA", "NA", "NA");
        String title = "Do Yoga!";
        habit.setTitle(title);
        Habit habit1 = getHabitobject(title, "NA", "NA", "NA");
        assertTrue(habit1.equals(habit));
    }

    @Test
    public void getReason() {
        String reason = "To stay fit!";
        Habit habit = getHabitobject("NA", reason, "NA", "NA");
        String reason1 = habit.getReason();
        assertEquals(reason, reason1);
    }

    @Test
    public void setReason() {
        Habit habit = getHabitobject("NA", "NA", "NA", "NA");
        String reason = "To stay fit!";
        habit.setReason(reason);
        Habit habit1 = getHabitobject("NA", reason, "NA", "NA");
        assertTrue(habit1.equals(habit));
    }

    @Test
    public void getStartDate() {
        String date = "04/22/2000";
        Habit habit = getHabitobject("NA", "NA", date, "NA");
        String date1 = habit.getStartDate();
        assertEquals(date, date1);
    }

    @Test
    public void setStartDate() {
        Habit habit = getHabitobject("NA", "NA", "NA", "NA");
        String date = "04/22/2000";
        habit.setStartDate(date);
        Habit habit1 = getHabitobject("NA", "NA", date, "NA");
        assertTrue(habit1.equals(habit));
    }

    @Test
    public void getHabitID() {
        String ID = "ABC9876";
        Habit habit = getHabitobject("NA", "NA", "NA", ID);
        String ID1 = habit.getHabitID();
        assertEquals(ID, ID1);
    }

    @Test
    public void setHabitID() {
        Habit habit = getHabitobject("NA", "NA", "NA", "NA");
        String ID = "ABC9876";
        habit.setHabitID(ID);
        Habit habit1 = getHabitobject("NA", "NA", "NA", ID);
        assertTrue(habit1.equals(habit));
    }

    @Test
    public void getHabitEvent() {
        Habit habit = getHabitobject("NA", "NA", "NA", "NA");
        HabitEvent habitevent = habit.getHabitEvent();
        assertNotNull("habit event exists", habitevent);
    }
}