
package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.pets.PetsContract.petsEntry;

public class PetsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "petsData.db";

    private static final int DATABASE_VERSION = 1;

    public PetsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + petsEntry.TABLE_NAME + " ("
                + petsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + petsEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + petsEntry.COLUMN_BREED + " TEXT, "
                + petsEntry.COLUMN_GENDER + " INTEGER NOT NULL, "
                + petsEntry.COLUMN_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
