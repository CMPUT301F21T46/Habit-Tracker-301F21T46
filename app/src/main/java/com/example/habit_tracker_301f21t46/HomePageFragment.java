package com.example.habit_tracker_301f21t46;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class HomePageFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    String userID;

    private ListView listView;
    private ListView listView2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage,container,false);

        // Getting FireBase Instances
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid(); // retrieve ID of current logged in user

        listView = view.findViewById(R.id.listView);
        listView.setAdapter(HabitData.getInstance().getHabitListAdapter());

        listView2 = view.findViewById(R.id.all_users_view_home);
        listView2.setAdapter(UsersData.getInstance().getAllUsersListAdapter());

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedUser = UsersData.getInstance().getUsersList().get(i).getEmail();

                DocumentReference documentReference = mStore.collection("Users").document(mAuth.getCurrentUser().getEmail());
                documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                        // Get the current user's followers
                        ArrayList<String> following = (ArrayList<String>) value.get("following");
                        ArrayList<String> followRequest = (ArrayList<String>) value.get("followRequest");
                        // if they are already following go to profile page
                        if (following.contains(selectedUser) || followRequest.contains(selectedUser)){
                            ProfileFragment nextFrag = new ProfileFragment();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                            // else go to follow request page
                            UsersData.getInstance().setSelectedUserIndex(i);
                            FollowPromptFragment nextFrag = new FollowPromptFragment();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });
            }
        });

        return view;
    }
}
