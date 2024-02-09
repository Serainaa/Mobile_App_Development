package com.example.myfood;

public class Ingredient {
    private String _name;
    private int _id;

    public Ingredient() {
    }

    public Ingredient(String name, int id) {
        this._name = name;
        this._id = id;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public void setId(int id) {
        this._id = id;
    }
    public int getId() {
        return this._id;
    }
}
