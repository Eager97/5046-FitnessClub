package com.example.fitnessclub.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey
    @NonNull  //should not be empty
    @ColumnInfo(name = "user_id")
    private String userId;

    @NonNull
    @ColumnInfo(name = "first_name")
    private String firstName;

    @NonNull
    @ColumnInfo(name = "last_name")
    private String lastName;

    public User(@NonNull String userID, @NonNull String firstName, @NonNull String lastName){
        this.userId = userID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

    }

    @NonNull
    public String getUserId(){
        return userId;
    }

    public void setUserId(@NonNull String userId){
        this.userId = userId;
    }

    @NonNull
    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(@NonNull String firstName){
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName(){
        return lastName;
    }

    public void setLastName(@NonNull String lastName){
        this.lastName = lastName;
    }
}
