package com.example.fitnessclub.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.fitnessclub.dao.TrainingDao;
import com.example.fitnessclub.entity.Training;
import com.example.fitnessclub.roomDataBase.TrainingRoomDataBase;

import java.util.List;

public class TrainingRepository {

    private TrainingDao mTrainingDao;
    private LiveData<List<Training>> mAllTraining;
    private List<Training> allTrainingList;

    public TrainingRepository(Application application){
        TrainingRoomDataBase db = TrainingRoomDataBase.getDatabase(application);
        mTrainingDao = db.trainingDao();
        mAllTraining = mTrainingDao.getTrainingByTrainingName();
        allTrainingList = mTrainingDao.getTrainingName();
    }

    // rewrite methods of DAO
    public LiveData<List<Training>> getmAllTraining(){
        return mAllTraining;
    }

    public List<Training> getAllTrainingList() {
        return allTrainingList;
    }

    public void insert(Training training){
        TrainingRoomDataBase.databaseWriteExecutor.execute(() -> {
            mTrainingDao.insert(training);
        }
        );
    }
}
