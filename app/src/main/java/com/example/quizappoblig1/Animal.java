package com.example.quizappoblig1;


import android.graphics.Bitmap;

public class Animal {

    private String name;
    private Bitmap image;
    public Animal(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }

    private String getName() {
        return this.name;
    }
    private Bitmap getImage() {
        return this.image;
    }
}
