package com.example.quizappoblig1;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Collections;


public class Database {

    protected ArrayList<Animal> list;

    public Database(Context context) {
        ArrayList<Animal> list = new ArrayList<Animal>();
        list.add(new Animal("Dinosaur", BitmapFactory.decodeResource(context.getResources(), R.drawable.dino)));
        list.add(new Animal("Dog", BitmapFactory.decodeResource(context.getResources(), R.drawable.doggo)));
        list.add(new Animal("Fish", BitmapFactory.decodeResource(context.getResources(), R.drawable.fish)));
        list.add(new Animal("Horse", BitmapFactory.decodeResource(context.getResources(), R.drawable.horse)));
    }
    private void addEntry(Animal entry) {
        list.add(entry);
    }

    public ArrayList<Animal> getNewQuestion() {
        Collections.shuffle(list);
        ArrayList<Animal> threeNames = new ArrayList<>();
        threeNames.add(list.get(1));
        threeNames.add(list.get(2));
        threeNames.add(list.get(3));
        return threeNames;
    }
}
