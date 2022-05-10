package com.example.fitnessclub.roomDataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fitnessclub.dao.TrainingDao;
import com.example.fitnessclub.entity.Training;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Training.class}, version = 1,exportSchema = false)
public abstract class TrainingRoomDataBase extends RoomDatabase {

    public abstract TrainingDao trainingDao();

    private static volatile TrainingRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TrainingRoomDataBase getDatabase(final Context context)  {
        if (INSTANCE == null){
            synchronized (TrainingRoomDataBase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TrainingRoomDataBase.class
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
                TrainingDao dao = INSTANCE.trainingDao();
                dao.deleteAll();

                Training training = new Training("001",10);
                dao.insert(training);
                training = new Training("002",20);
                dao.insert(training);
            });
        }
    };
}
