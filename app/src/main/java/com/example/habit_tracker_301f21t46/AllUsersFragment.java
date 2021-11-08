package com.example.habit_tracker_301f21t46;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AllUsersFragment extends Fragment {
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_users,container,false);

        listView = view.findViewById(R.id.all_users_view);
        listView.setAdapter(UsersData.getInstance().getAllUsersListAdapter());

        return view;
    }
}
