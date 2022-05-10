package com.example.fitnessclub;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.fitnessclub.entity.Training;
import com.example.fitnessclub.viewModel.TrainingViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class WorkManagerUpload extends Worker {
    public WorkManagerUpload(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {

        String trainingListJsonStr = getInputData().getString("TrainingList");

        // Do the work here--in this case, upload the images.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Training");

        Gson gson = new Gson();
        Type trainingMapType = new TypeToken<Map<String, Training>>(){}.getType();

        Map<String, Training> trainingMap = gson.fromJson(trainingListJsonStr,trainingMapType);

        ref.setValue(trainingMap);

        // Indicate whether the work finished successfully with the Result
        return Result.success();
    }
}
