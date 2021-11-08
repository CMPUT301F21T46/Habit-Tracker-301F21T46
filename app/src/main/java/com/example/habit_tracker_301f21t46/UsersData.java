package com.example.habit_tracker_301f21t46;

import android.content.Context;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class UsersData {
    //FireBase
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;

    private static UsersData instance;
    private ArrayList<User> usersList;
    private AllUsersAdapter allUsersListAdapter;

    private int selectedUserIndex;

    private UsersData(Context activity, int layout){
        //UI
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        //Adapters
        usersList = new ArrayList<User>();
        allUsersListAdapter = new AllUsersAdapter(activity, R.layout.all_users_view, usersList);
        final CollectionReference collectionReference = mStore.collection("Users");

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                // Clear the old list
                usersList.clear();
                for(QueryDocumentSnapshot doc: queryDocumentSnapshots)
                {
                    String userName = (String) doc.getData().get("name");
                    String userEmail = (String) doc.getData().get("email");
                    String userPassword = (String) doc.getData().get("password");

                    // todo: following fucntionalities

                    usersList.add(new User(userName, userEmail, userPassword));
                }
                allUsersListAdapter.notifyDataSetChanged();
            }
        });
    }

    public static UsersData getInstance(Context activity, int layout){
        //Will expose HabitData as a singleton so data can be accessed from other classes
        if (instance == null){
            instance = new UsersData(activity,layout);
        }
        return instance;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public FirebaseFirestore getmStore() {
        return mStore;
    }

    public void setmStore(FirebaseFirestore mStore) {
        this.mStore = mStore;
    }

    public static UsersData getInstance() {
        return instance;
    }

    public static void setInstance(UsersData instance) {
        UsersData.instance = instance;
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    public AllUsersAdapter getAllUsersListAdapter() {
        return allUsersListAdapter;
    }

    public void setAllUsersListAdapter(AllUsersAdapter allUsersListAdapter) {
        this.allUsersListAdapter = allUsersListAdapter;
    }

    public int getSelectedUserIndex() {
        return selectedUserIndex;
    }

    public void setSelectedUserIndex(int selectedUserIndex) {
        this.selectedUserIndex = selectedUserIndex;
    }
}
