package com.example.myfood;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.view.MenuItem;
import androidx.annotation.NonNull;


import android.content.Intent;
public class MyIngredientsActivity extends AppCompatActivity {

    private AutoCompleteTextView editIngredient;
    private Button buttonAdd;
    private Button buttonDelete;
    private Button buttonSearch;
    private LinearLayout layoutIngredients;
    private List<String> predefinedIngredients;
    private List<String> selectedIngredients;

    // Define an array of ingredients for suggestions
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ingredients);

        ActionBar actionBar = getSupportActionBar();

        // Showing the back button in action bar.
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Getting elements from layout.
        editIngredient = findViewById(R.id.editIngredient);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSearch = findViewById(R.id.buttonSearch);
        layoutIngredients = findViewById(R.id.layoutIngredients);

        // Initialize predefined ingredients list.
        predefinedIngredients = Arrays.asList("water", "oil", "salt", "pepper");

        // Initialize selected ingredients list.
        selectedIngredients = new ArrayList<>();

        // Create an ArrayAdapter with the list of ingredients.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, ingredientNames);

        // Set the adapter for the AutoCompleteTextView.
        editIngredient.setAdapter(adapter);

        // Set an item click listener for the AutoCompleteTextView.
        editIngredient.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            String selectedIngredient = parent.getItemAtPosition(position).toString().trim().toLowerCase();
            addIngredient(selectedIngredient);
        });
        buttonAdd.setOnClickListener((View v) -> {
            String ingredient = editIngredient.getText().toString().trim().toLowerCase();
            addIngredient(ingredient);
        });

        buttonDelete.setOnClickListener(v -> deleteIngredients());

        // Populate predefined ingredients as checked checkboxes.
        populatePredefinedIngredients();

        // Set click listener for the "Search" button.
        buttonSearch.setOnClickListener(v -> {

            CharSequence[] selectedIngredientsArray = selectedIngredients.toArray(new CharSequence[selectedIngredients.size()]);

            // Pass the selected ingredients array to the next activity.
            Intent intent = new Intent(MyIngredientsActivity.this, RecipesActivity.class);
            intent.putExtra("selectedIngredients", selectedIngredientsArray);
            startActivity(intent);
        });
    }

    // Populate predefined list with four basic ingredients given.
    private void populatePredefinedIngredients() {
        for (String ingredient : predefinedIngredients) {
            addIngredient(ingredient);
        }
    }

    // Create a checkbox for each ingredient added and also update the list with the selected ingredients.
    private void addIngredient(String ingredient) {
        if (!ingredient.isEmpty()) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(ingredient);
            checkBox.setChecked(true); // Set the checkbox as checked.
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    selectedIngredients.add(ingredient);
                } else {
                    selectedIngredients.remove(ingredient);
                }
            });

            // Create a LinearLayout.LayoutParams for the CheckBox.
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            // Add margins to the LinearLayout.LayoutParams.
            int margin = getResources().getDimensionPixelSize(R.dimen.checkbox_margin);
            layoutParams.setMargins(margin, margin, margin, margin);

            layoutIngredients.addView(checkBox, layoutParams);

            selectedIngredients.add(ingredient);

            // Clear the input field.
            editIngredient.getText().clear();
        }
    }

    // Delete the checkboxes of the selected ingredients and remove them from selected ingredients list.
    private void deleteIngredients() {
        for (int i = layoutIngredients.getChildCount() - 1; i >= 0; i--) {
            View view = layoutIngredients.getChildAt(i);
            if (view instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()) {
                    layoutIngredients.removeViewAt(i);
                    selectedIngredients.remove(checkBox.getText().toString());
                }
            } else if (view instanceof TextView) {
                TextView textView = (TextView) view;
                layoutIngredients.removeViewAt(i);
                selectedIngredients.remove(textView.getText().toString());
            }
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

