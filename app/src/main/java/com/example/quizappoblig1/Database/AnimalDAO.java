package com.example.quizappoblig1.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.quizappoblig1.Database.Animal;
import com.example.quizappoblig1.ViewModels.AnimalAsyncTask;

import java.util.List;

@Dao
public interface AnimalDAO {

    @Query("Select * from animal")
    LiveData<List<Animal>> getAnimalList();

    @Insert
    void insertAnimal(Animal animal);

    @Delete
    void deleteAnimal(Animal animal);

    @Query("Select * from animal Order by RANDOM() limit 3")
    List<Animal> getThree(AnimalAsyncTask.AsyncResponse response);

    @Insert
    void insertAll(Animal... animalArray);

    @Query("Select * from animal where name = :name")
    List<Animal> find(String name);
}
