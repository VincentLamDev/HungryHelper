package project.comp3717.hungryhelper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe);
        setTitle("Recipe Name");

        ImageView imageView = (ImageView)findViewById(R.id.recipeImage);
        TextView ingredientText = (TextView)findViewById(R.id.ingredients);
        TextView instructionText = (TextView)findViewById(R.id.instructions);


    }

}
