package com.example.fitnessclub.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fitnessclub.entity.User;
import com.example.fitnessclub.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    private final LiveData<List<User>> mAllUsers;

    public UserViewModel (Application application){
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    public void insert(User user){
        mRepository.insert(user);
    }
}
