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
    void insertAnimal(Animal animal);

    @Delete
    void deleteAnimal(Animal animal);

    @Query("Select * from animal Order by RANDOM() limit 3")
    List<Animal> getThree();

    @Insert
    void insertAll(Animal... animalArray);
}
