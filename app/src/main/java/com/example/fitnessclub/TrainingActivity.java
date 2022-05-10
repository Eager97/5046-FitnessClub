package com.example.fitnessclub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessclub.databinding.ActivityTrainingscreenBinding;
import com.example.fitnessclub.entity.Training;
import com.example.fitnessclub.viewModel.TrainingViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TrainingActivity extends AppCompatActivity {

    private ActivityTrainingscreenBinding binding;
    private TrainingViewModel mTrainingViewModel;
    public static final int NEW_TRAINING_ACTIVITY_REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrainingscreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mTrainingViewModel = new ViewModelProvider(this).get(TrainingViewModel.class);

        RecyclerView recycleView = binding.recyclerview;
        final TrainingListAdapter adapter = new TrainingListAdapter(new TrainingListAdapter.TrainingDiff());
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(TrainingActivity.this, NewTrainingActivity.class);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == NEW_TRAINING_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            Training training = new Training(data.getStringExtra("trainingName"),data.getDoubleExtra("trainingDuration",0));
            mTrainingViewModel.insert(training);
        }else {
            Toast.makeText(this, "The training is not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
