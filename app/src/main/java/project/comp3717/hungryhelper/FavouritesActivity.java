package project.comp3717.hungryhelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class FavouritesActivity extends AppCompatActivity {

    protected ListView lv;
    protected ListAdapter adapter;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);


        try {
            db = (new MyDBHandler(this, null, null, 1)).getWritableDatabase();
            cursor = db.rawQuery("SELECT * FROM Favourites"  , null);
            adapter = new SimpleCursorAdapter(this, R.layout.custom_listview, cursor,
                    new String[] { "NAME", "IMAGE"}, new int[] {
                    R.id.recipeTitle, R.id.imageView});
            lv = (ListView) findViewById(R.id.listView);
            lv.setAdapter(adapter);
            lv.setTextFilterEnabled(true);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    detail(position);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        styleList();

    }

    public void styleList(){
        // lv.setTextColor(Color.argb(255,255,165,0));
        //lv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
    }

    public void detail(int position) {
        int image = 0;
        String _id = "";
        String name = "";
        String ingredients = "";
        String instructions = "";
        if (cursor.moveToFirst()) {
            cursor.moveToPosition(position);
            image = cursor.getInt(cursor.getColumnIndex("IMAGE"));
            name = cursor.getString(cursor.getColumnIndex("NAME"));
            ingredients = cursor.getString(cursor.getColumnIndex("INGREDIENTS"));
            instructions = cursor.getString(cursor.getColumnIndex("INSTRUCTIONS"));
        }

        Intent iIntent = new Intent(this, DisplayRecipeActivity.class);
        iIntent.putExtra("recipeImage", image);
        iIntent.putExtra("recipeName", name);
        iIntent.putExtra("recipeIngredients", ingredients);
        iIntent.putExtra("recipeInstructions", instructions);
        setResult(RESULT_OK, iIntent);
        startActivityForResult(iIntent, 99);
    }


    protected void onListItemClick(ListView l, final View v, int position, long id) {

        onListItemClick(l, v, position, id);
        //int itemPosition     = position;
        final String  itemValue    = (String) l.getItemAtPosition(position);
        Log.d("im clicking on ", itemValue);
        Intent intent = new Intent(this, DisplayRecipeActivity.class);
        //intent.putExtra("courses",itemValue);
        startActivity(intent);
    }
}
