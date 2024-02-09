package com.example.myfood;

public class Recipe {
    private int _recipeId;
    private String _title;
    private String _description;

    public Recipe(int id, String title, String description) {
        this._recipeId = id;
        this._title = title;
        this._description = description;
    }

    public Recipe() {
    }

    public void setId(int id) {
        this._recipeId = id;
    }

    public int getId() {
        return this._recipeId;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public String getTitle() {
        return this._title;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public String getDescription() {
        return this._description;
    }
}