package com.example.myfood;

public class Recipe_ingredient {

    private int _recipeId;
    private int _ingredientId;

    public Recipe_ingredient() {
    }

    public Recipe_ingredient(int recipeId, int ingrId) {
        this._recipeId = recipeId;
        this._ingredientId = ingrId;
    }

    public void setRecipeId(int id) {
        this._recipeId = id;
    }

    public int getRecipeId() {
        return this._recipeId;
    }

    public void setIngredientId(int id) {
        this._ingredientId = id;
    }

    public int getIngredientId() {
        return this._ingredientId;
    }
}
