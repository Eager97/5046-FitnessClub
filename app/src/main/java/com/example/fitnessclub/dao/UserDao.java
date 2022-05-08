package com.example.fitnessclub.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fitnessclub.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * FROM user_table ORDER BY first_name ASC")
    List<User> getUsersByFirstName();

    @Query("SELECT * FROM user_table ORDER BY user_id ASC")
    LiveData<List<User>> getUserOrderByIDInLiveData();
}
