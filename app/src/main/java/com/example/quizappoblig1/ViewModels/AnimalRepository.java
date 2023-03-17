package com.example.quizappoblig1.ViewModels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quizappoblig1.Animal;
import com.example.quizappoblig1.Database.AnimalDAO;
import com.example.quizappoblig1.Database.AnimalDatabase;

import java.util.List;

public class AnimalRepository {

    private MutableLiveData<List<Animal>> searchResults = new MutableLiveData<>();
    private LiveData<List<Animal>> allAnimals;
    private AnimalDAO animalDao;

    public LiveData<List<Animal>> getAllAnimals() {
        return allAnimals;
    }

    public MutableLiveData<List<Animal>> getSearchResults() {
        return searchResults;
    }

    public AnimalRepository(Application application) {
        AnimalDatabase db;
        db = AnimalDatabase.getInstance(application.getApplicationContext());
        animalDao = db.animalDao();
        allAnimals = animalDao.getAnimalList();
    }

    public void insertAnimal(Animal animal) {
        InsertAsyncTask task = new InsertAsyncTask(animalDao);
        task.execute(animal);
    }

    public void findAnimal(String name) {
        QueryAsyncTask task = new QueryAsyncTask(animalDao);
        task.delegate = this;
        task.execute(name);
    }

    private static class InsertAsyncTask extends AsyncTask<Animal, Void, Void> {

        private AnimalDAO asyncTaskDao;

        InsertAsyncTask(AnimalDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Animal... params) {
            asyncTaskDao.insertAll(params[0]);
            return null;
        }
    }

    private void asyncFinished(List<Animal> results) {
        searchResults.setValue(results);
    }

    private static class QueryAsyncTask extends
            AsyncTask<String, Void, List<Animal>> {

        private AnimalDAO asyncTaskDao;
        private AnimalRepository delegate = null;

        QueryAsyncTask(AnimalDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<Animal> doInBackground(final String... params) {
            return asyncTaskDao.find(params[0]);
        }

        @Override
        protected void onPostExecute(List<Animal> result) {
            delegate.asyncFinished(result);
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Animal, void, void> {
        private AnimalDAO asyncTaskDao;
        DeleteAsyncTask(AnimalDAO dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected void doInBackground(final Animal... params) {
            asyncTaskDao.deleteAnimal(params[0]);
            return null;
        }
    }
}
