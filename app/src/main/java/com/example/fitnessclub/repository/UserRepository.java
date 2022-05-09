package com.example.fitnessclub.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.fitnessclub.dao.UserDao;
import com.example.fitnessclub.entity.User;
import com.example.fitnessclub.roomDataBase.UserRoomDataBase;

import java.util.List;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;

    public UserRepository(Application application){
        UserRoomDataBase db = UserRoomDataBase.getDatabase(application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getTrainingByTrainingName();

    }

    // rewrite methods of DAO
    public LiveData<List<User>> getAllUsers(){
        return mAllUsers;
    }


    public void insert(User user){
        UserRoomDataBase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        }
        );
    }
}
