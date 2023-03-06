package com.example.quizappoblig1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.io.ByteArrayOutputStream;

@Database(entities = Animal.class, version = 1)
public abstract class AnimalDatabase extends RoomDatabase {

    private static final String DB_NAME = "animal_db";
    private static AnimalDatabase instance;

    public static synchronized AnimalDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AnimalDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();

            Drawable d = ResourcesCompat.getDrawable(context.getResources(), R.drawable.dino, null);
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] image = stream.toByteArray();
            instance.animalDao().insertAnimal(new Animal("Dinosaur", image));

            d = ResourcesCompat.getDrawable(context.getResources(), R.drawable.doggo, null);
            bitmap = ((BitmapDrawable)d).getBitmap();
            stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            image = stream.toByteArray();
            instance.animalDao().insertAnimal(new Animal("Dog", image));

            d = ResourcesCompat.getDrawable(context.getResources(), R.drawable.fish, null);
            bitmap = ((BitmapDrawable)d).getBitmap();
            stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            image = stream.toByteArray();
            instance.animalDao().insertAnimal(new Animal("Fish", image));

            d = ResourcesCompat.getDrawable(context.getResources(), R.drawable.horse, null);
            bitmap = ((BitmapDrawable)d).getBitmap();
            stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            image = stream.toByteArray();
            instance.animalDao().insertAnimal(new Animal("Horse", image));

        }
        return instance;
    }
    public abstract AnimalDAO animalDao();
}
