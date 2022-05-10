package com.example.fitnessclub;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessclub.databinding.ActivityNewTrainingBinding;

public class NewTrainingActivity extends AppCompatActivity {

    private ActivityNewTrainingBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewTrainingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }


}
