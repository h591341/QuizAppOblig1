package com.example.quizappoblig1.Database;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.quizappoblig1.R;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.concurrent.Executors;

@Database(entities = Animal.class, version = 1, exportSchema = false)
public abstract class AnimalDatabase extends RoomDatabase {

    private static AnimalDatabase instance;

    public abstract AnimalDAO animalDao();

    public static synchronized AnimalDatabase getInstance(Context context) {
        if(instance == null) {
            instance = buildDatabase(context);
        }
        return instance;
    }

    private static AnimalDatabase buildDatabase(final Context context) {
        AnimalDatabase db = Room.databaseBuilder(context,
                        AnimalDatabase.class,
                        "animal_db")
                .fallbackToDestructiveMigration()
                .build();
        try {
            if(db.animalDao().find("Dinosaur") != null) {
                Animal[] animals = populateData(context.getResources());
                db.animalDao().insertAll(animals);
            }
        } catch (Exception e) {

        }
        return db;
    }

    public static Animal[] populateData(Resources resources) {
        Animal[] animalArray = new Animal[4];

        Drawable d = ResourcesCompat.getDrawable(resources, R.drawable.dino, null);
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] image = stream.toByteArray();
        animalArray[0] = new Animal("Dinosaur", image);

        d = ResourcesCompat.getDrawable(resources, R.drawable.doggo, null);
        bitmap = ((BitmapDrawable)d).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        image = stream.toByteArray();
        animalArray[1] = new Animal("Dog", image);

        d = ResourcesCompat.getDrawable(resources, R.drawable.fish, null);
        bitmap = ((BitmapDrawable)d).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        image = stream.toByteArray();
        animalArray[2] = new Animal("Fish", image);

        d = ResourcesCompat.getDrawable(resources, R.drawable.horse, null);
        bitmap = ((BitmapDrawable)d).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        image = stream.toByteArray();
        animalArray[3] = new Animal("Horse", image);

        return animalArray;
    }
}
