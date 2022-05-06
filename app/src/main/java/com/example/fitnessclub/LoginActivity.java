package com.example.fitnessclub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessclub.databinding.ActivityLoginscreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginscreenBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseListener;

    @Override
    protected void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        binding = ActivityLoginscreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        //jump to the signup screen when user click the get started text
        binding.signupTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        binding.loginButton.setOnClickListener(v -> {
            mAuth.signInWithEmailAndPassword(binding.loginUserNameText.getText().toString(), binding.loginPasswordText.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Authentication success.",
                                        Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(LoginActivity.this,MainActivity.class));


                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        });

        firebaseListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Toast.makeText(LoginActivity.this, "You have Log in the app.",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }

        };

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (firebaseListener != null){
            mAuth.removeAuthStateListener(firebaseListener);
        }
    }
}

