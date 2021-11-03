package com.example.habit_tracker_301f21t46;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.google.firebase.FirebaseApp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class HabitDataTest {

    public HabitData getInstance() {
        Context context = ApplicationProvider.getApplicationContext();
        FirebaseApp.initializeApp(context);
        HabitData habitdata;
        habitdata = HabitData.getInstance(context, 1);
        return habitdata;
    }

    @Test
    public void testGetInstance() {
        HabitData habitData = getInstance();
        assertTrue(habitData.getInstance() instanceof HabitData);
        HabitData instance = habitData.getInstance();
        assertNotNull(instance);
    }

    @Test
    public void setInstance() {
    }

    @Test
    public void getHabitList() {
    }

    @Test
    public void setHabitList() {
    }

    @Test
    public void getHabitListAdapter() {
    }

    @Test
    public void setHabitListAdapter() {
    }

    @Test
    public void getSingleHabitListAdapter() {
    }

    @Test
    public void setSingleHabitListAdapter() {
    }

    @Test
    public void getSelectedHabitIndex() {
    }

    @Test
    public void setSelectedHabitIndex() {
    }
}