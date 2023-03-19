package com.example.quizappoblig1.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Arrays;

@Entity(tableName = "animal")
public class Animal {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "animalId")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private final byte[] image;

    public Animal(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public String getName() { return this.name; }

    public byte[] getImage() { return this.image; }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {  return "Name="+this.name+" imageByte="+ Arrays.toString(this.image);  }

}
