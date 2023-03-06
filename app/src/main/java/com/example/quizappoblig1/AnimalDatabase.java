package com.example.quizappoblig1;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Animal.class, version = 1)
public abstract class AnimalDatabase extends RoomDatabase {

    private static final String DB_NAME = "animal_db";
    private static AnimalDatabase instance;

    public static synchronized AnimalDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AnimalDatabase.class, DB_NAME)
                    .build();
        }
        return instance;
    }
    public abstract AnimalDAO animalDao();
}
