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

public class RegisterActivity extends AppCompatActivity {
    //FireBase Auth
    private FirebaseAuth mAuth;
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

                /* Todo: Implement user data checks
                String confirmPassword = userConfirmPassword.getText().toString();
                 */

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // Sign in success
                            Log.d(TAG, "createUserWithEmail:success");
                            /* Todo: store name into FireStore, use userID for collection, later profile pic might be added

                             */
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
