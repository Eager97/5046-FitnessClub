package com.example.fitnessclub.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fitnessclub.entity.Training;
import com.example.fitnessclub.repository.TrainingRepository;

import java.util.List;

public class TrainingViewModel extends AndroidViewModel {

    private TrainingRepository mRepository;
    private final LiveData<List<Training>> mAllTraining;
    private List<Training> allTrainingList;

    public TrainingViewModel(Application application){
        super(application);
        mRepository = new TrainingRepository(application);
        mAllTraining = mRepository.getmAllTraining();
        allTrainingList = mRepository.getAllTrainingList();
    }

    LiveData<List<Training>> getAllTraining() {
        return mAllTraining;
    }

    public List<Training> getAllTrainingList() {
        return allTrainingList;
    }

    public void insert(Training training){
        mRepository.insert(training);
    }
}
