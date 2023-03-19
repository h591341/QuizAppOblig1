package com.example.quizappoblig1.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.quizappoblig1.Database.AnimalDatabase;
import com.example.quizappoblig1.Database.FindAsyncResponse;
import com.example.quizappoblig1.ViewModels.AnimalAsyncTask;
import com.example.quizappoblig1.Database.Animal;

import java.util.List;

public class QuizDataViewModel extends AndroidViewModel {

    private final AnimalAsyncTask repository;
    private AnimalDatabase db = AnimalDatabase.getInstance(getApplication().getApplicationContext());
    private LiveData<List<Animal>> allAnimals;
    private Byte[] picture;
    private int score;
    private int attempts;
    private String btn1Name;
    private String btn2Name;
    private String btn3Name;
    private int correctInt;


    public QuizDataViewModel(@NonNull Application application) {
        super(application);
        repository = new AnimalAsyncTask(db.animalDao());
        LiveData<List<Animal>> allAnimals = repository.getAnimalList();
        score = 0;
        attempts = 0;
    }

    public LiveData<List<Animal>> getAllAnimals() {
        return allAnimals;
    }

    public void insertAnimal(Animal animal) {
        repository.insertAnimal(animal);
    }

    public void findAnimalByName(String name, FindAsyncResponse response) {
        repository.find(name, response);
    }

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getBtn1Name() {
        return btn1Name;
    }

    public void setBtn1Name(String btn1Name) {
        this.btn1Name = btn1Name;
    }

    public String getBtn2Name() {
        return btn2Name;
    }

    public void setBtn2Name(String btn2Name) {
        this.btn2Name = btn2Name;
    }

    public String getBtn3Name() {
        return btn3Name;
    }

    public void setBtn3Name(String btn3Name) {
        this.btn3Name = btn3Name;
    }

    public int getCorrectInt() {
        return correctInt;
    }

    public void setCorrectInt(int correctInt) {
        this.correctInt = correctInt;
    }
}

