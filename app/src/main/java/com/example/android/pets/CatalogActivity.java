package com.example.android.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.pets.PetsContract.petsEntry;
import com.example.android.pets.data.PetsDbHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    PetsDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new PetsDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {

//        PetsDbHelper mDbHelper = new PetsDbHelper(this);
//
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                petsEntry._ID,
                petsEntry.COLUMN_NAME,
                petsEntry.COLUMN_BREED,
                petsEntry.COLUMN_WEIGHT,
                petsEntry.COLUMN_GENDER};

        /**   Cursor cursor = db.query(
         petsEntry.TABLE_NAME,
         projection,
         null,
         null,
         null,
         null,
         null);

         */
        TextView displayView = (TextView) findViewById(R.id.text_view_pet);

        Cursor cursor = getContentResolver().query(petsEntry.CONTENT_URI, projection, null, null, null);


        try {

            displayView.setText("Number of pets in the Shelter : " + cursor.getCount() + "\n");

            displayView.append(
                    petsEntry._ID + " - " +
                            petsEntry.COLUMN_NAME + " - " +
                            petsEntry.COLUMN_BREED + " - " +
                            petsEntry.COLUMN_GENDER + " - " +
                            petsEntry.COLUMN_WEIGHT + " - \n");

            int id_index = cursor.getColumnIndex(petsEntry._ID);
            int name_index = cursor.getColumnIndex(petsEntry.COLUMN_NAME);
            int breed_index = cursor.getColumnIndex(petsEntry.COLUMN_BREED);
            int gender_index = cursor.getColumnIndex(petsEntry.COLUMN_GENDER);
            int weight_index = cursor.getColumnIndex(petsEntry.COLUMN_WEIGHT);


            while (cursor.moveToNext()) {
                int id = cursor.getInt(id_index);
                String name = cursor.getString(name_index);
                String breed = cursor.getString(breed_index);
                int gender = cursor.getInt(gender_index);
                int weight = cursor.getInt(weight_index);

                displayView.append("\n " +
                        id + " - " +
                        name + " - " +
                        breed + " - " +
                        gender + " - " +
                        weight + " - ");
            }


        } finally {

            cursor.close();
        }
    }

    private void insertPet() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(petsEntry.COLUMN_NAME, "Totto");
        values.put(petsEntry.COLUMN_BREED, "Terrier");
        values.put(petsEntry.COLUMN_WEIGHT, 7);
        values.put(petsEntry.COLUMN_GENDER, 1);

        Uri uri = getContentResolver().insert(petsEntry.CONTENT_URI, values);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
            case R.id.action_delete_all_entries:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}