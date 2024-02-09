package com.example.myfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyDBHandler extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "recipes.db";

        // Recipes table.
        private static final String TABLE_RECIPES = "recipes";
        private static final String COLUMN_RECIPES_ID = "recipes_id";
        private static final String COLUMN_TITLE = "title";
        private static final String COLUMN_DESCRIPTION = "description";

        // Ingredients table.
        private static final String TABLE_INGREDIENTS = "ingredients";
        private static final String COLUMN_INGREDIENTS_ID = "ingredients_id";
        private static final String COLUMN_NAME = "name";

        // Recipe_ingredients table.
        private static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
        private static final String COLUMN_RECIPE_ID = "recipe_id";
        private static final String COLUMN_INGREDIENT_ID = "ingredient_id";

        private SQLiteDatabase database;
        private static String[] ingredientNames = {
                "flour",
                "sugar",
                "butter",
                "vanilla extract",
                "baking powder",
                "salt",
                "spinach",
                "pepper",
                "olive oil",
                "garlic",
                "onion",
                "chicken",
                "beef",
                "pork",
                "rice",
                "pasta",
                "tomato",
                "cheese",
                "milk",
                "eggs",
                "bacon",
                "tomato sauce",
                "cheese feta",
                "brown sugar",
                "lettuce",
                "croutons",
                "cheese parmesan",
                "basil",
                "ceasar dressing"
        };

        public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);

        }
        @Override
        public void onCreate(SQLiteDatabase db) {

            // Create the "recipes" table
            String createRecipesTableQuery = "CREATE TABLE IF NOT EXISTS " +
                    TABLE_RECIPES + " (" +
                    COLUMN_RECIPES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TITLE + " TEXT," +
                    COLUMN_DESCRIPTION + " TEXT" +
                    ")";
            db.execSQL(createRecipesTableQuery);

            // Insert a recipe for Margherita Pizza
            String insertRecipe1 = "INSERT INTO " + TABLE_RECIPES + " (" + COLUMN_TITLE + ", " + COLUMN_DESCRIPTION + ") " +
                    "VALUES ('Margherita Pizza', 'A classic pizza topped with fresh tomatoes, mozzarella cheese, and basil. " +
                    "To cook the Margherita Pizza, follow these steps:\n" +
                    "1. Preheat the oven to 450°F (230°C).\n" +
                    "2. Roll out the pizza dough into a circle.\n" +
                    "3. Spread tomato sauce over the dough.\n" +
                    "4. Sprinkle grated mozzarella cheese over the sauce.\n" +
                    "5. Arrange sliced tomatoes and fresh basil leaves on top.\n" +
                    "6. Bake the pizza in the preheated oven for 12-15 minutes or until the crust is golden and the cheese is melted.');";
            db.execSQL(insertRecipe1);

            // Insert a recipe for Chocolate Chip Cookies
            String insertRecipe2 = "INSERT INTO " + TABLE_RECIPES + "(" + COLUMN_TITLE + ", " + COLUMN_DESCRIPTION + ") " +
                    "VALUES ('Chocolate Chip Cookies', 'Delicious homemade cookies loaded with chocolate chips. " +
                    "To bake Chocolate Chip Cookies, follow these steps:\n" +
                    "1. Preheat the oven to 350°F (175°C).\n" +
                    "2. In a mixing bowl, cream together butter, sugar, and brown sugar.\n" +
                    "3. Beat in eggs and vanilla extract.\n" +
                    "4. In a separate bowl, combine flour, baking soda, and salt.\n" +
                    "5. Gradually add the dry ingredients to the butter mixture, mixing well.\n" +
                    "6. Stir in chocolate chips.\n" +
                    "7. Drop rounded spoonfuls of dough onto a greased baking sheet.\n" +
                    "8. Bake for 10-12 minutes or until golden brown.');";
            db.execSQL(insertRecipe2);

            // Insert a recipe for Caesar Salad
            String insertRecipe3 = "INSERT INTO " + TABLE_RECIPES + "(" + COLUMN_TITLE + ", " + COLUMN_DESCRIPTION + ") " +
                    "VALUES ('Caesar Salad', 'A classic salad made with romaine lettuce, croutons, Parmesan cheese, and Caesar dressing. " +
                    "To prepare Caesar Salad, follow these steps:\n" +
                    "1. Wash and dry the romaine lettuce leaves.\n" +
                    "2. Tear the lettuce into bite-sized pieces and place them in a large salad bowl.\n" +
                    "3. Add homemade or store-bought Caesar dressing to the lettuce and toss to coat.\n" +
                    "4. Sprinkle croutons over the salad.\n" +
                    "5. Grate Parmesan cheese over the top.\n" +
                    "6. Serve the Caesar Salad chilled.');";
            db.execSQL(insertRecipe3);

            // Insert Greek Moussaka
            String insertRecipe4 = "INSERT INTO " + TABLE_RECIPES +
                    "(" + COLUMN_TITLE + ", " + COLUMN_DESCRIPTION + ") VALUES " +
                    "('Greek Moussaka', 'Greek Moussaka is a classic Greek dish that brings together layers of roasted eggplant, seasoned ground lamb, and creamy bechamel sauce. The preparation involves the following steps:\n\n" +
                    "1. Preheat the oven and prepare the ingredients.\n" +
                    "2. Slice the eggplants and sprinkle them with salt. Leave them to drain and remove excess moisture.\n" +
                    "3. In a skillet, brown the ground lamb with onions, garlic, and spices.\n" +
                    "4. Layer the roasted eggplant slices and the cooked lamb mixture in a baking dish.\n" +
                    "5. Prepare the bechamel sauce and pour it over the layers.\n" +
                    "6. Bake the moussaka in the preheated oven until golden and bubbling.\n\n" +
                    "Greek Moussaka is a delicious and hearty dish that is often served with a side salad or crusty bread.')";
            db.execSQL(insertRecipe4);

            // Insert Spanakopita (Greek Spinach Pie)
            String insertRecipe5 = "INSERT INTO " + TABLE_RECIPES +
                    "(" + COLUMN_TITLE + ", " + COLUMN_DESCRIPTION + ") VALUES " +
                    "('Spanakopita', 'Spanakopita, also known as Greek Spinach Pie, is a delightful dish that combines flaky phyllo pastry with a flavorful spinach and feta filling. The preparation involves the following steps:\n\n" +
                    "1. Prepare the spinach by washing, blanching, and squeezing out excess moisture.\n" +
                    "2. Sauté onions and garlic until fragrant, then combine them with the spinach, feta cheese, herbs, and seasonings.\n" +
                    "3. Layer sheets of phyllo pastry, brushing each layer with melted butter or olive oil.\n" +
                    "4. Spread the spinach and feta mixture evenly over the phyllo layers.\n" +
                    "5. Fold the phyllo layers over the filling to create a parcel.\n" +
                    "6. Brush the top layer with more butter or oil, then bake until golden and crispy.\n\n" +
                    "Spanakopita is a popular appetizer or snack in Greek cuisine, enjoyed for its delicate flavors and crispy texture.')";
            db.execSQL(insertRecipe5);


            // Create the "ingredients" table
            String createIngredientsTableQuery = "CREATE TABLE IF NOT EXISTS " +
                    TABLE_INGREDIENTS + "(" +
                    "ingredients_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT" +
                    ");";
            db.execSQL(createIngredientsTableQuery);

            ContentValues values = new ContentValues();
            for (String name : ingredientNames) {
                values.clear();
                values.put("name", name);
                db.insert(TABLE_INGREDIENTS, null, values);
            }

            // Create the "recipe_ingredients" table
            String createRecipeIngredientsTableQuery = "CREATE TABLE IF NOT EXISTS " +
                    TABLE_RECIPE_INGREDIENTS + "(" + COLUMN_RECIPE_ID +
                    " INTEGER, " + COLUMN_INGREDIENT_ID +
                    " INTEGER, " +
                    "FOREIGN KEY (recipe_id) REFERENCES recipes(_id), " +
                    "FOREIGN KEY (ingredient_id) REFERENCES ingredients(_id)" +
                    ");";
            db.execSQL(createRecipeIngredientsTableQuery);

            // Insert recipe-ingredient associations for some recipes.
            String insertRecipeIngredients = "INSERT INTO " + TABLE_RECIPE_INGREDIENTS +
                    "(" + COLUMN_RECIPE_ID + ", " + COLUMN_INGREDIENT_ID + ") VALUES " +
                    "(1, 1), " + // Margherita Pizza requires "Pizza Dough"
                    "(1, 22), " + // Margherita Pizza requires "Tomato Sauce"
                    "(1, 18), " + // Margherita Pizza requires "Mozzarella Cheese"
                    "(1, 17), " + // Margherita Pizza requires "Tomatoes"
                    "(1, 28), " + // Margherita Pizza requires "Fresh Basil"
                    "(1, 9), " + // Margherita Pizza requires "Olive oil"
                    "(1, 6), " +  // Margherita Pizza requires "Salt"
                    "(2, 3), " +  // Chocolate Chip Cookies require "Butter"
                    "(2, 2), " +  // Chocolate Chip Cookies require "Sugar"
                    "(2, 24), " +  // Chocolate Chip Cookies require "Brown Sugar"
                    "(2, 20), " + // Chocolate Chip Cookies require "Eggs"
                    "(2, 11), " + // Chocolate Chip Cookies require "Vanilla Extract"
                    "(2, 4), " + // Chocolate Chip Cookies require "Flour"
                    "(2, 5), " + // Chocolate Chip Cookies require "Baking Soda"
                    "(2, 6), " +  // Chocolate Chip Cookies require "Salt"
                    "(3, 6), " + // Caesar Salad requires "Salt"
                    "(3, 20), " + // Caesar Salad requires "Eggs"
                    "(3, 21), " + // Caesar Salad requires "Bacon"
                    "(3, 25), " + // Caesar Salad requires "Romaine Lettuce"
                    "(3, 26), " + // Caesar Salad requires "Croutons"
                    "(3, 27), " + // Caesar Salad requires "Parmesan Cheese"
                    "(3, 29), " + // Caesar Salad requires "Caesar Dressing"
                    "(4, 6), " + // Mousakas requires "Salt"
                    "(4, 9), " + // Mousakas requires "Olive oil"
                    "(4, 10), " + // Mousakas requires "Garlic"
                    "(4, 11), " + // Mousakas requires "Onion"
                    "(4, 20), " + // Mousakas requires "Eggs" etc.
                    "(5, 7), " + // Spanakopita requires "Spinach"
                    "(5, 9), " + // Spanakopita requires "Olive oil"
                    "(5, 10), " + // Spanakopita requires "Garlic"
                    "(5, 11), " + // Spanakopita requires "Onion"
                    "(5, 20), " + // Spanakopita requires "Eggs"
                    "(5, 23);"; // Spanakopita requires "Feta Cheese"
            db.execSQL(insertRecipeIngredients);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        //Return ingredient_id given the ingredient name.
        public int findIngredientId(String selectedIngredientName) {
            String query = "SELECT * FROM " + TABLE_INGREDIENTS + " WHERE " +
                    COLUMN_NAME + " = '" + selectedIngredientName + "'";
            //SQLiteDatabase db = this.getReadableDatabase();
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            Ingredient ingredient = new Ingredient();
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                ingredient.setId(Integer.parseInt(cursor.getString(0)));
                cursor.close();
            } else {
                ingredient.setId(0);
            }
            db.close();
            return ingredient.getId();
        }

        //Return recipe_id given the ingredient_id.
        public ArrayList<Integer> findRecipeId(int ingredient_id) {
            String query = "SELECT * FROM " + TABLE_RECIPE_INGREDIENTS + " WHERE " +
                    COLUMN_INGREDIENT_ID + " = '" + ingredient_id + "'";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            ArrayList<Integer> recipe_ids = new ArrayList<Integer>();
            if (cursor.moveToFirst()) {
                do {
                    Recipe_ingredient recipeIngredient = new Recipe_ingredient();
                    recipeIngredient.setRecipeId(Integer.parseInt(cursor.getString(0)));
                    recipe_ids.add(recipeIngredient.getRecipeId());
                } while (cursor.moveToNext());
                cursor.close();
            }
            db.close();
            return recipe_ids;
        }

        //Return recipes given the selected Ingredients array.
        public ArrayList<Recipe> getRecipesWithIngredients(CharSequence[] selectedIngredients) {
            int a;
            ArrayList<Integer> my_list = new ArrayList<>();
            ArrayList<Integer> final_list_recipes_id = new ArrayList<>();
            ArrayList<Recipe> final_recipes = new ArrayList<>();

            // Given the name of the ingredient get a list of the recipes id in which the ingredient is used.
            for (CharSequence element : selectedIngredients){
                a = findIngredientId(element.toString());
                my_list = findRecipeId(a);
                final_list_recipes_id.addAll(my_list);
            }
            ArrayList<String> recipes_titles = new ArrayList();

            // Find recipe objects based on new_List.
            for (int i: final_list_recipes_id) {
                // Query to find the distinct recipes from table recipes.
                String query = "SELECT * FROM " + TABLE_RECIPES + " WHERE " +
                        COLUMN_RECIPES_ID + " = '" + i + "'";
                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(query, null);
                if (cursor.moveToFirst()) {
                    cursor.moveToFirst();
                    if (recipes_titles.contains(cursor.getString(1))){
                        System.out.println("There is already this recipe.");
                    } else {
                        recipes_titles.add(cursor.getString(1));
                        Recipe recipe = new Recipe();
                        recipe.setTitle(cursor.getString(1));
                        recipe.setDescription(cursor.getString(2));
                        final_recipes.add(recipe);
                        cursor.close();
                    }
                    db.close();
                }
            }
            return final_recipes;
        }
}
