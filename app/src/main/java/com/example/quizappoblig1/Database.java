package com.example.quizappoblig1;

import static java.util.Collections.shuffle;


import android.util.Log;
import java.util.ArrayList;

public class Database {

    protected ArrayList<Animal> list;

    public Database() {
                this.list = new ArrayList<>();
                list.add(new Animal("Dinosaur", R.drawable.dino));
                list.add(new Animal("Dog", R.drawable.doggo));
                list.add(new Animal("Fish", R.drawable.fish));
                list.add(new Animal("Horse", R.drawable.horse));
        };

    private void addEntry(Animal entry) {
        list.add(entry);
    }

    public ArrayList<Animal> getNewQuestion() {
        shuffle(this.list);
        ArrayList<Animal> threeNames = new ArrayList<>();
        threeNames.add(this.list.get(0));
        threeNames.add(this.list.get(1));
        threeNames.add(this.list.get(2));
        return threeNames;
    }
}
