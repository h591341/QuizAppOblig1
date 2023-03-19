package com.example.quizappoblig1.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.quizappoblig1.Database.Animal;
import com.example.quizappoblig1.Database.AnimalDatabase;
import com.example.quizappoblig1.Database.FindAsyncResponse;

import java.util.List;

public class MainMenuViewModel extends AndroidViewModel {

    private AnimalDatabase db = AnimalDatabase.getInstance(getApplication().getApplicationContext());
    private AnimalAsyncTask repository;
    private LiveData<List<Animal>> allAnimals;

    public MainMenuViewModel(Application application) {
        super(application);
        repository = new AnimalAsyncTask(db.animalDao());
        allAnimals = repository.getAnimalList();
    }

    public LiveData<List<Animal>> getAllAnimals() {
        return allAnimals;
    }

    public void findAnimalByName(String name, FindAsyncResponse response) {
        repository.find(name, response);
    }
    public void deleteAnimal(Animal animal) {
        repository.deleteAnimal(animal);
    }

}
