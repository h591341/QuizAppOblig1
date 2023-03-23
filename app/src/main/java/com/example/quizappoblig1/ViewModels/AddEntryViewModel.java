package com.example.quizappoblig1.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.quizappoblig1.Database.Animal;
import com.example.quizappoblig1.Database.AnimalDatabase;

import java.util.List;

public class AddEntryViewModel extends AndroidViewModel {

    private AnimalAsyncTask repository;
    private AnimalDatabase db = AnimalDatabase.getInstance(getApplication().getApplicationContext());
    private LiveData<Animal> allAnimals;


    public AddEntryViewModel(@NonNull Application application) {
        super(application);
        repository = new AnimalAsyncTask(db.animalDao());
        LiveData<List<Animal>> allAnimals = db.animalDao().getAnimalList();
    }

    public void insertAnimal(Animal animal) { db.animalDao().insertAnimal(animal); }

    public void findAnimalByName(String name) { db.animalDao().find(name); }

}
