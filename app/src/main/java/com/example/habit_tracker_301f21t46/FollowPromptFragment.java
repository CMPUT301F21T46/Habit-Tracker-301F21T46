package com.example.habit_tracker_301f21t46;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.Map;

public class FollowPromptFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    String userID;

    private Button sendFollowRequest;
    private TextView nameView, emailView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow_prompt,container,false);

        // Getting FireBase Instances
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid(); // retrieve ID of current logged in user

        User selectedUser = UsersData.getInstance().getUsersList().get(UsersData.getInstance().getSelectedUserIndex());

        sendFollowRequest = view.findViewById(R.id.follow_request_button);
        nameView = view.findViewById(R.id.follow_user_name);
        emailView = view.findViewById(R.id.follow_user_email);

        nameView.setText(selectedUser.getName());
        emailView.setText(selectedUser.getEmail());

        sendFollowRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = mStore.collection("Users").document(mAuth.getCurrentUser().getEmail());
                documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        ArrayList<String> followRequest = (ArrayList<String>) value.get("following");
                        followRequest.add(selectedUser.getEmail());

                        //Map<String, Object> user = new HashMap<>();
                        //user.put("name", selectedUser.getName());
                        //user.put("email", selectedUser.getEmail());
                        //user.put("password", selectedUser.getPassword());
                        //user.put("followRequest", followRequest);
                        documentReference.update("followRequest", selectedUser.getEmail());

                        HomePageFragment nextFrag = new HomePageFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    }
                });
            }
        });

        return view;
    }
}
