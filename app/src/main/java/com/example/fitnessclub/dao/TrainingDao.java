package com.example.fitnessclub.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fitnessclub.entity.Training;

import java.util.List;

@Dao
public interface TrainingDao {

    @Insert
    void insert(Training training);

    @Query("DELETE FROM training_table")
    void deleteAll();

    @Query("SELECT * FROM training_table ORDER BY training_duration ASC")
    List<Training> getTrainingDuration();

    @Query("SELECT * FROM training_table ORDER BY training_name DESC")
    List<Training> getTrainingName();

    @Query("SELECT * FROM training_table ORDER BY training_name ASC")
    LiveData<List<Training>> getTrainingByTrainingName();
}
