package com.example.quizappoblig1;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Animal implements Parcelable {

    private String name;
    private int mData;
    private int image;
    public Animal(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }
    public int getImage() { return this.image; }
    @Override
    public String toString() {  return "Name="+this.name+" imageInt="+this.image;  }



    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }
    public static final Parcelable.Creator<Animal> CREATOR
            = new Parcelable.Creator<Animal>() {
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };
    private Animal(Parcel in) {
        mData = in.readInt();
    }
}
