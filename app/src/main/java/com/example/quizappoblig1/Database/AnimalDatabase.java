package com.example.quizappoblig1.Database;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.res.ResourcesCompat;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.quizappoblig1.R;
import java.io.ByteArrayOutputStream;

@Database(entities = Animal.class, version = 2, exportSchema = false)
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
        return Room.databaseBuilder(context,
                AnimalDatabase.class,
                "animal_db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Log.d("AnimalDatabase", "Database created, populating data...");
                        Animal[] animals = populateData(context.getResources());
                        AnimalDAO dao = getInstance(context).animalDao();
                        dao.insertAll(animals);
                    }
                })
                .fallbackToDestructiveMigration()
                .build();
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
