
package com.example.quizappoblig1.ViewModels;

import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.quizappoblig1.Database.Animal;
import com.example.quizappoblig1.Database.AnimalDAO;
import com.example.quizappoblig1.Database.FindAsyncResponse;

import java.util.List;

public class AnimalAsyncTask {

    private AnimalDAO animalDao;

    public AnimalAsyncTask(AnimalDAO animalDao) {
        this.animalDao = animalDao;
    }

    public void insertAnimal(Animal animal) {
        new InsertAnimalAsyncTask(animalDao).execute(animal);
    }

    public void deleteAnimal(Animal animal) {
        new DeleteAnimalAsyncTask(animalDao).execute(animal);
    }

    public void getThree(GetThreeAsyncResponse response) {
        new GetThreeAsyncTask(animalDao, response).execute();
    }

    public void insertAll(Animal... animals) {
        new InsertAllAsyncTask(animalDao).execute(animals);
    }

    public void find(String name, FindAsyncResponse response) {
        new FindAsyncTask(animalDao, response).execute(name);
    }

    public LiveData<List<Animal>> getAnimalList() {
        return animalDao.getAnimalList();
    }

    private static class InsertAnimalAsyncTask extends AsyncTask<Animal, Void, Void> {
        private AnimalDAO animalDao;
        private InsertAnimalAsyncTask(AnimalDAO animalDao) {
            this.animalDao = animalDao;
        }
        @Override
        protected Void doInBackground(Animal... animals) {
            animalDao.insertAnimal(animals[0]);
            return null;
        }
    }

    private static class DeleteAnimalAsyncTask extends AsyncTask<Animal, Void, Void> {
        private AnimalDAO animalDao;
        private DeleteAnimalAsyncTask(AnimalDAO animalDao) {
            this.animalDao = animalDao;
        }
        @Override
        protected Void doInBackground(Animal... animals) {
            animalDao.deleteAnimal(animals[0]);
            return null;
        }
    }

    private static class GetThreeAsyncTask extends AsyncTask<Void, Void, List<Animal>> {
        private AnimalDAO animalDao;
        private GetThreeAsyncResponse response;

        private GetThreeAsyncTask(AnimalDAO animalDao, GetThreeAsyncResponse response) {
            this.animalDao = animalDao;
            this.response = response;
        }

        @Override
        protected List<Animal> doInBackground(Void... voids) {
            return animalDao.getThree();
        }

        @Override
        protected void onPostExecute(List<Animal> animals) {
            super.onPostExecute(animals);
            response.onGetThreeAsyncFinished(animals);
        }
    }

    private static class InsertAllAsyncTask extends AsyncTask<Animal, Void, Void> {
        private AnimalDAO animalDao;
        private InsertAllAsyncTask(AnimalDAO animalDao) {
            this.animalDao = animalDao;
        }
        @Override
        protected Void doInBackground(Animal... animals) {
            animalDao.insertAll(animals);
            return null;
        }
    }

    private static class FindAsyncTask extends AsyncTask<String, Void, List<Animal>> {
        private AnimalDAO animalDao;
        private FindAsyncResponse response;

        private FindAsyncTask(AnimalDAO animalDao, FindAsyncResponse response) {
            this.animalDao = animalDao;
            this.response = response;
        }

        @Override
        protected List<Animal> doInBackground(String... strings) {
            return animalDao.find(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Animal> animals) {
            super.onPostExecute(animals);
            response.onFindAsyncFinished(animals);
        }
    }

    public interface GetThreeAsyncResponse {
        void onGetThreeAsyncFinished(List<Animal> animals);