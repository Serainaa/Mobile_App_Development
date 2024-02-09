package com.example.myfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import java.util.ArrayList;



public class RecipesActivity extends AppCompatActivity {
    private LinearLayout layoutRecipes;
    private ArrayList<Recipe> recipes;
    private MyDBHandler dbHandler;
//    private Button buttonShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        this.layoutRecipes = findViewById(R.id.layoutRecipes);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar.
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            CharSequence[] selectedIngredients = extras.getCharSequenceArray("selectedIngredients");

            // Create dbHandler.
            dbHandler = new MyDBHandler(this, null, null, 1);
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            db.close();
            recipes = dbHandler.getRecipesWithIngredients(selectedIngredients);
            System.out.println(recipes);
            displayRecipes(recipes);
        }
    }

    // Display title of the recipes in the UI as buttons.
    private void displayRecipes(ArrayList<Recipe> recipe_array_list) {
        for (Recipe recipe : recipe_array_list) {
            LinearLayout recipeLayout = new LinearLayout(this);
            recipeLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.recipe_margin));

            Button titleTextView = new Button(this);
            titleTextView.setText(recipe.getTitle());
            titleTextView.setTextSize(18);
            recipeLayout.addView(titleTextView);

            titleTextView.setOnClickListener(v -> {
                // Starting next activity.
                Intent intent = new Intent(RecipesActivity.this, DescriptionActivity.class);
                intent.putExtra("recipe_title", recipe.getTitle());
                intent.putExtra("recipe_description", recipe.getDescription());
                startActivity(intent);
            });

            this.layoutRecipes.addView(recipeLayout, layoutParams);
        }
    }

    // This event will enable the back.
    // Function to the button on press.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}