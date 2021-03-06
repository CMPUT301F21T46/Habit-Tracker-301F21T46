package com.example.habit_tracker_301f21t46;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * RegisterActivity.java handles the login screen. It is linked to the activity_register layout.
 * It gets username, useremail, password from the UI via a listener and the
 * uses the Firebase Authentication API to register a new user. These is also a button for going to the
 * login screen.
 */

public class RegisterActivity extends AppCompatActivity {
    //FireBase Auth
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    //UI
    EditText userName, userEmail, userPassword, userConfirmPassword;
    Button registerButton;
    TextView goToLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        // Connect UI
        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);
        userConfirmPassword = findViewById(R.id.user_password_confirm);
        registerButton = findViewById(R.id.register_button);
        goToLoginButton = findViewById(R.id.goto_login_button);

        // Check user is not already logged in, if so start MainActivity
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();
                String confirmPassword = userConfirmPassword.getText().toString();
                String name = userName.getText().toString();

                /* Todo: Implement user data checks
                String confirmPassword = userConfirmPassword.getText().toString();
                 */

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // Sign in success
                            Log.d(TAG, "createUserWithEmail:success");

                            DocumentReference documentReference = mStore.collection("Users").document(email);
                            Map<String, Object> user = new HashMap<>();
                            user.put("name", name);
                            user.put("email", email);
                            user.put("password", password);
                            ArrayList<String> following = new ArrayList<>();
                            user.put("following", following);
                            ArrayList<String> followers = new ArrayList<>();
                            user.put("followers", followers);
                            ArrayList<String> followRequest = new ArrayList<>();
                            user.put("followRequest", followRequest);
                            documentReference.set(user);

                            // Start MainActivity
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            // Sign in failed
                            Log.d(TAG, "createUserWithEmail:failed");
                        }
                    }
                });
            }
        });

        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Goto Login Activity
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}
