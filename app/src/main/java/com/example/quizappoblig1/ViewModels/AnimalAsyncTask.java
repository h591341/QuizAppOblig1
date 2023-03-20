
package com.example.quizappoblig1.ViewModels;

import android.os.AsyncTask;
import com.example.quizappoblig1.Database.Animal;
import com.example.quizappoblig1.Database.AnimalDAO;

import java.util.List;

public class AnimalAsyncTask extends AsyncTask<Void, Void, List<Animal>> {

    private AnimalDAO animalDao;
    private AsyncResponse delegate;

    public AnimalAsyncTask(AnimalDAO animalDao, AsyncResponse delegate) {
        this.animalDao = animalDao;
        this.delegate = delegate;
    }

    @Override
    protected List<Animal> doInBackground(Void... voids) {
        return animalDao.getThree(new AsyncResponse() {
            @Override
            public void processFinish(List<Animal> output) {

            }
        });
    }

    public void insert(Animal animal) {
        new InsertAsyncTask(animalDao).execute(animal);
    }

    public void delete(Animal animal) {
        new DeleteAsyncTask(animalDao).execute(animal);
    }

    private static class InsertAsyncTask extends AsyncTask<Animal, Void, Void> {

        private AnimalDAO animalDao;

        public InsertAsyncTask(AnimalDAO animalDao) {
            this.animalDao = animalDao;
        }

        @Override
        protected Void doInBackground(Animal... animals) {
            animalDao.insertAnimal(animals[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Animal, Void, Void> {

        private AnimalDAO animalDao;

        public DeleteAsyncTask(AnimalDAO animalDao) {
            this.animalDao = animalDao;
        }

        @Override
        protected Void doInBackground(Animal... animals) {
            animalDao.deleteAnimal(animals[0]);
            return null;
        }
    }

    public interface AsyncResponse {
        void processFinish(List<Animal> output);
    }
}