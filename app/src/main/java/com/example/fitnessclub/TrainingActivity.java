package com.example.fitnessclub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.fitnessclub.databinding.ActivityTrainingscreenBinding;
import com.example.fitnessclub.entity.Training;
import com.example.fitnessclub.viewModel.TrainingViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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


        List<Training> trainingList = mTrainingViewModel.getAllTrainingList();
        Map<String,Training> trainingMap = new HashMap<>();
        trainingList.forEach(s -> trainingMap.put(s.getTrainingName(),s));
        Gson gson = new Gson();
        String json =  gson.toJson(trainingMap);

        Data.Builder uploadBuilder = new Data.Builder();
        Map<String, Object> map = new HashMap<>();
        map.put("TrainingList", json);
        uploadBuilder.putAll(map);
        Data data = uploadBuilder.build();

        WorkRequest saveRequest =
                new PeriodicWorkRequest.Builder(WorkManagerUpload.class,
                        24, TimeUnit.HOURS)
                        .setInputData(data)
                        .build();
        WorkManager.getInstance(this).enqueue(saveRequest);
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
