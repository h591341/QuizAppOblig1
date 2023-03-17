package com.example.quizappoblig1.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.quizappoblig1.Animal;

import java.util.List;

public class MainMenuViewModel extends AndroidViewModel {

    private AnimalRepository repository;
    private LiveData<List<Animal>> allAnimals;

    public MainMenuViewModel(Application application) {
        super(application);
        repository = new AnimalRepository(application);
        allAnimals = repository.getAllAnimals();
    }

    public LiveData<List<Animal>> getAllAnimals() {
        return allAnimals;
    }

    public void findAnimal(String name) {
        repository.findAnimal(name);
    }

    public void deleteAnimal(Animal animal) {
        repository.deleteAnimal(animal);
    }

}
