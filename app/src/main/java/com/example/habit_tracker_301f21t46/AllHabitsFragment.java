package com.example.habit_tracker_301f21t46;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Fragment shows a list of all habits of a user. Linked to fragment_all_habits layout.
 * Uses habits_list as a ListView and this is linked to the list of habits in Habitdata for a user.
 */
public class AllHabitsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_habits,container,false);

        HabitData habitData = HabitData.getInstance();

        ListView listView = (ListView) view.findViewById(R.id.habits_list);
        listView.setAdapter(HabitData.getInstance().getSingleHabitListAdapter());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Todo: click is only registering on the name
                HabitData.getInstance().setSelectedHabitIndex(i);
                HabitDetailsFragment nextFrag = new HabitDetailsFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });



        // adding habits
        final FloatingActionButton addCityButton = view.findViewById(R.id.add_habit_button);
        addCityButton.setOnClickListener((v) -> {
            AddingHabbitFragment nextFrag = new AddingHabbitFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}
