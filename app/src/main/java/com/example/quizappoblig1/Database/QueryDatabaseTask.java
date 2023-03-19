package com.example.quizappoblig1.Database;

import android.os.AsyncTask;
import java.util.List;

public class QueryDatabaseTask extends AsyncTask<Void, Void, List<Animal>> {
    private AnimalDatabase db;

    public QueryDatabaseTask(AnimalDatabase db) {
        this.db = db;
    }

    @Override
    protected List<Animal> doInBackground(Void... voids) {
        return db.animalDao().getThree();
    }
}
