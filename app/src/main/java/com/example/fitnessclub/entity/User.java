package com.example.fitnessclub.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey
    @NonNull  //should not be empty
    @ColumnInfo(name = "training_name")
    private String trainingName;

    @NonNull
    @ColumnInfo(name = "training_duration")
    private double trainingDuration;


    public User(@NonNull String trainingName, @NonNull double trainingDuration){
        this.trainingName = trainingName;
        this.trainingDuration = trainingDuration;

    }


    @NonNull
    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(@NonNull String trainingName) {
        this.trainingName = trainingName;
    }

    public double getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(double trainingDuration) {
        this.trainingDuration = trainingDuration;
    }
}
