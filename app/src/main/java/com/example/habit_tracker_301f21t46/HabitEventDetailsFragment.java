package com.example.habit_tracker_301f21t46;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HabitEventDetailsFragment extends Fragment {

    private static HabitEventDetailsFragment instance;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit_event_details, container, false);

        instance = this;
        System.out.println("hello");
        return view;
    }

    public static HabitEventDetailsFragment getInstance() {
        return instance;
    }

}
