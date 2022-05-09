package com.example.fitnessclub.roomDataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fitnessclub.dao.UserDao;
import com.example.fitnessclub.entity.User;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class UserRoomDataBase extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile UserRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UserRoomDataBase getDatabase(final Context context)  {
        if (INSTANCE == null){
            synchronized (UserRoomDataBase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),UserRoomDataBase.class
                            ,"user_database").addCallback(sRoomDatabaseCallback).build();

                }
            }
        }
        return INSTANCE;
    }

    //add two users when database create
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                UserDao dao = INSTANCE.userDao();
                dao.deleteAll();

                User user = new User("001",10);
                dao.insert(user);
                user = new User("002",20);
                dao.insert(user);
            });
        }
    };
}
