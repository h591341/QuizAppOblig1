package com.example.quizappoblig1;

import static java.util.Collections.shuffle;


import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class Database implements Parcelable {

    protected ArrayList<Animal> list;
    private int mData;

    public Database() {
                this.list = new ArrayList<>();
                list.add(new Animal("Dinosaur", R.drawable.dino));
                list.add(new Animal("Dog", R.drawable.doggo));
                list.add(new Animal("Fish", R.drawable.fish));
                list.add(new Animal("Horse", R.drawable.horse));
        };

    public void addEntry(Animal entry) {
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

    public ArrayList<Animal> getList() {
        return this.list;
    }


    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }
    public static final Parcelable.Creator<Database> CREATOR
            = new Parcelable.Creator<Database>() {
        public Database createFromParcel(Parcel in) {
            return new Database(in);
        }
        public Database[] newArray(int size) {
            return new Database[size];
        }
    };
    private Database(Parcel in) {
        mData = in.readInt();
    }

}
