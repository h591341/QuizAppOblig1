package com.example.quizappoblig1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AnimalDAO {

    @Query("Select * from animal")
    List<Animal> getAnimalList();

    @Insert
    public void insertAnimal(Animal animal);

    @Delete
    public void deleteAnimal(Animal animal);

    @Query("Select * from animal Order by RANDOM() limit 3")
    public List<Animal> getThree();
}
