package com.example.fitnessclub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessclub.databinding.ActivityLoginscreenBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginscreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        binding = ActivityLoginscreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //jump to the signup screen when user click the get started text
        binding.signupTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
            startActivity(intent);
            }
        });
    }




}
