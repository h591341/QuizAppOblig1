package com.example.quizappoblig1;

import static java.util.Collections.shuffle;


import android.util.Log;
import java.util.ArrayList;

public class Database {

    protected ArrayList<Animal> list;

    public Database() {
        ArrayList<Animal> list = new ArrayList<Animal>();
        Log.d("ArrayListEmpty",list.toString());
        list.add(new Animal("Dinosaur", R.drawable.dino));
        Log.d("ArrayListDino", list.toString());
        list.add(new Animal("Dog", R.drawable.doggo));
        list.add(new Animal("Fish", R.drawable.fish));
        list.add(new Animal("Horse", R.drawable.horse));
    }

    private void addEntry(Animal entry) {
        list.add(entry);
    }

    public ArrayList<Animal> getNewQuestion() {
        shuffle(list);
        ArrayList<Animal> threeNames = new ArrayList<>();
        threeNames.add(list.get(1));
        threeNames.add(list.get(2));
        threeNames.add(list.get(3));
        return threeNames;
    }
}
