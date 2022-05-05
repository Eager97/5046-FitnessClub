package com.example.fitnessclub;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessclub.databinding.ActivitySignupscreenBinding;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupscreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        binding = ActivitySignupscreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }

}
